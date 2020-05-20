package com.vf.task.groovy;

import com.vf.utils.lang.Assert;
import groovy.lang.MetaClass;
import groovy.lang.MetaMethod;
import groovy.lang.Script;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Groovy脚本注册管理器，提供注册（移除）BeanDefinition及获取（销毁）Bean
 *
 * @author levin
 * @date 2020/5/13
 * @see org.springframework.beans.factory.config.BeanDefinition
 * @see org.springframework.beans.factory.support.BeanDefinitionRegistry
 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory
 * @since 1.0.0
 */
public class GroovyScriptBeanManager implements BeanFactoryAware, ApplicationContextAware, BeanClassLoaderAware {
    /**
     * prefix
     */
    public static final String PREFIX_BEAN_DEFINITION = "vin-groovy";
    /**
     * default groovy shell args key
     */
    public static final String KEY_ARGS = "args";

    private ApplicationContext applicationContext;
    private GroovyScriptManager groovyScriptManager;
    private BeanDefinitionRegistry beanDefinitionRegistry;

    /**
     * script bean manager
     *
     * @param groovyScriptManager groovy script manager
     */
    public GroovyScriptBeanManager(GroovyScriptManager groovyScriptManager) {
        if (null == groovyScriptManager) {
            throw new IllegalArgumentException("groovyScriptManager must be not null");
        }
        this.groovyScriptManager = groovyScriptManager;
    }

    /**
     * register script bean
     *
     * @param scriptName script name
     * @return the script class.script shell could be a subclass of {@link Script}
     * @throws GroovyScriptException exception
     */
    public Class registerScriptBean(String scriptName) throws GroovyScriptException {
        Assert.isBlank(scriptName, "script name must be not blank");
        GroovyScriptManager.ScriptCacheEntry scriptCacheEntry = groovyScriptManager.loadScriptByName(scriptName);
        Class clazz = scriptCacheEntry.getScriptClass();
        String scriptBeanName = getScriptBeanName(scriptName);
        boolean existBeanDefinition = beanDefinitionRegistry.containsBeanDefinition(scriptBeanName);
        if (scriptCacheEntry.isSourceNewer() || !existBeanDefinition) {
            if (existBeanDefinition) {
                beanDefinitionRegistry.removeBeanDefinition(scriptBeanName);
            }
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
            AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
            beanDefinitionRegistry.registerBeanDefinition(scriptBeanName, beanDefinition);
        }
        return clazz;
    }

    /**
     * register bean and get bean from applicationContext
     *
     * @param scriptName script name
     * @return script class
     * @throws BeansException
     * @throws GroovyScriptException
     */
    Object getBean(String scriptName) throws BeansException, GroovyScriptException {
        String scriptBeanName = getScriptBeanName(scriptName);
        boolean contain = beanDefinitionRegistry.containsBeanDefinition(scriptBeanName);
        if (!contain) {
            registerScriptBean(scriptBeanName);
        }
        Object bean = applicationContext.getBean(scriptBeanName);
        return bean;
    }

    /**
     * get bean name
     *
     * @param scriptName script name
     * @return bean name
     */
    private String getScriptBeanName(String scriptName) {
        return PREFIX_BEAN_DEFINITION + scriptName;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (!(applicationContext instanceof BeanDefinitionRegistry)) {
            throw new RuntimeException("application is not a BeanDefinitionRegistry");
        }
        this.applicationContext = applicationContext;
        this.beanDefinitionRegistry = (BeanDefinitionRegistry) applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {

    }

    /**
     * run groovy script,only for subclass of {@link Script}.<b/>
     * run groovy class see {@link #runMethod(String, String, Object...) }
     *
     * @param scriptName script name
     * @param params     script params
     * @return script shell result
     * @throws GroovyScriptException exception
     * @see #runMethod(String, String, Object...)
     * @see Script#run()
     * @see Script#setProperty(String, Object)
     */
    public Object runScript(String scriptName, Map<String, Object> params) throws GroovyScriptException {
        Class clazz = registerScriptBean(scriptName);
        Object bean = getBean(scriptName);
        if (!(bean instanceof Script)) {
            throw new UnsupportedOperationException(scriptName + " must be a groovy script shell");
        }
        Script script = (Script) bean;
        if (null != params) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                script.setProperty(param.getKey(), param.getValue());
            }
        }
        return script.run();
    }

    /**
     * run groovy script,only for subclass of {@link Script}.<b/>
     * run groovy class see {@link #runMethod(String, String, Object...) }
     *
     * @param scriptName script name
     * @param args       script params,script can use with <code>args</code>
     * @return script shell result
     * @throws GroovyScriptException exception
     * @see #runMethod(String, String, Object...)
     * @see Script#run()
     * @see Script#setProperty(String, Object)
     */
    public Object runScript(String scriptName, Object... args) throws GroovyScriptException {
        Map<String, Object> param = new HashMap<>();
        param.put(KEY_ARGS, args);
        return runScript(scriptName, param);
    }

    /**
     * run groovy shell method or groovy class method.<b/>
     * best match method signature
     *
     * @param scriptName script name
     * @param args       method args
     * @return result
     * @throws GroovyScriptException exception
     * @see #runScript(String, Map)
     */
    public Object runMethod(String scriptName, String methodName, Object... args) throws GroovyScriptException {
        Assert.isBlank(methodName, " method name must be not null");
        Class clazz = registerScriptBean(scriptName);
        Object script = getBean(scriptName);
        if (script instanceof Script) {
            MetaClass metaClass = ((Script) script).getMetaClass();
            List<MetaMethod> methods = metaClass.getMethods();
            List<MetaMethod> collect = methods.stream().filter(t -> methodName.equals(t.getName())
                    && t.getNativeParameterTypes().length == args.length).collect(Collectors.toList());
            Assert.isEmpty(collect, methodName + " method does not exist.");
            //TODO  get best match method
            Object invoke = collect.get(0).invoke(script, args);
            return invoke;
        } else {
            Method[] methods = script.getClass().getMethods();
            List<Method> collect = Stream.of(methods).filter(t -> t.getName().equals(methodName)
                    && t.getParameterTypes().length == args.length).collect(Collectors.toList());
            Assert.isEmpty(collect, methodName + " method does not exist.");
            Object invoke = null;
            try {
                invoke = collect.get(0).invoke(script, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw GroovyScriptException.runError(scriptName, e);
            }
            return invoke;
        }
    }
}
