package com.vf.task.groovy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

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

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {

    }
}
