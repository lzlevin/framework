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
        boolean b = method.isAnnotationPresent(Log.class) || method.getDeclaringClass().isAnnotationPresent(Log.class);
        return b;
    }

}
