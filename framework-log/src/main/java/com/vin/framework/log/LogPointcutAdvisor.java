package com.vin.framework.log;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.aopalliance.intercept.Joinpoint;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author levin
 * @since 1.0.0
 */
public class LogPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor implements BeanPostProcessor {

    /**
     * 日志发布器
     */
    public static final LogPublisher publisher = new LogPublisher();

    /**
     * 默认构造，注入拦截器
     */
    public LogPointcutAdvisor() {
        setAdvice(new LogInterceptor());
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

    /**
     * @author levin
     * @since 1.0.0
     */
    public static class LogInterceptor implements MethodInterceptor {
        /**
         * Implement this method to perform extra treatments before and
         * after the invocation. Polite implementations would certainly
         * like to invoke {@link Joinpoint#proceed()}.
         *
         * @param invocation the method invocation joinpoint
         * @return the result of the call to {@link Joinpoint#proceed()};
         * might be intercepted by the interceptor
         * @throws Throwable if the interceptors or the target object
         *                   throws an exception
         */
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            Method method = invocation.getMethod();
            Log clazzLog = method.getDeclaringClass().getAnnotation(Log.class);
            Log methodLog = method.getAnnotation(Log.class);
            LogEvent logEvent = new LogEvent(invocation.getMethod());
            logEvent.setBatch(IdUtil.fastSimpleUUID());
            if (null != methodLog) {
                if (StrUtil.isBlank(methodLog.function()) && null != clazzLog) {
                    logEvent.setFunction(clazzLog.function());
                } else {
                    logEvent.setFunction(methodLog.function());
                }
                if (StrUtil.isBlank(methodLog.action()) && null != clazzLog) {
                    logEvent.setFunction(clazzLog.action());
                } else {
                    logEvent.setFunction(methodLog.action());
                }
                if (StrUtil.isBlank(methodLog.description()) && null != clazzLog) {
                    logEvent.setFunction(clazzLog.description());
                } else {
                    logEvent.setFunction(methodLog.description());
                }
            } else {
                logEvent.setFunction(clazzLog.function());
                logEvent.setAction(clazzLog.action());
                logEvent.setDescription(clazzLog.description());
            }
            logEvent.setClazz(method.getDeclaringClass());
            logEvent.setMethod(method);
            logEvent.setRequestTime(LocalDateTime.now());
            if (null != methodLog ? methodLog.request() : clazzLog.request()) {
                logEvent.setRequest(invocation.getArguments());
            }
            publisher.before(logEvent);
            Object proceed = null;
            try {
                proceed = invocation.proceed();
            } catch (Throwable throwable) {
                logEvent.setThrowable(throwable);
                logEvent.setSuccess(false);
                publisher.afterThrowing(logEvent);
                throw throwable;
            } finally {
                if (null == logEvent.getSuccess()) {
                    logEvent.setSuccess(true);
                }
                logEvent.setResponseTime(LocalDateTime.now());
                if (null != methodLog ? methodLog.response() : clazzLog.response()) {
                    logEvent.setResponse(proceed);
                }
                publisher.after(logEvent);
            }
            return proceed;
        }


    }
}
