package com.vin.framework.log;

import com.vin.framework.log.annotation.Log;
import org.aopalliance.intercept.Interceptor;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * 日志切面
 *
 * @author levin
 * @since 1.0.0
 */
public class LogPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {

    /**
     * 默认构造，注入拦截器
     */
    public LogPointcutAdvisor(Interceptor interceptor) {
        setAdvice(interceptor);
    }

    /**
     * Perform static checking whether the given method matches.
     * <p>If this returns {@code false} or if the {@link #isRuntime()}
     * method returns {@code false}, no runtime check (i.e. no
     * {@link #matches(Method, Class, Object[])} call)
     * will be made.
     *
     * @param method      the candidate method
     * @param targetClass the target class
     * @return whether or not this method matches statically
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        Log methodAnnotation = method.getAnnotation(Log.class);
        Log clazzAnnotation = method.getDeclaringClass().getAnnotation(Log.class);
        if (null == methodAnnotation && null == clazzAnnotation) {
            return false;
        }
        //如果父log不为空则先检查父配置
        if (null != clazzAnnotation) {
            if (clazzAnnotation.ignore()) {
                return false;
            }
            //父不忽略，则检查子配置
            else if (null != methodAnnotation) {
                return !methodAnnotation.ignore();
            } else {
                return true;
            }
        } else {
            return !methodAnnotation.ignore();
        }
    }

}
