package com.vf.log;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.vf.core.request.RequestHolder;
import com.vf.core.user.UserHolder;
import com.vf.log.annotation.Log;
import com.vf.log.event.*;
import com.vf.log.publisher.LogPublisher;
import org.aopalliance.intercept.Joinpoint;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 日志拦截器
 *
 * @author levin
 * @since 1.0.0
 */
public class LogInterceptor implements MethodInterceptor {

    /**
     * 事件发布器
     */
    private final LogPublisher publisher;

    /**
     * 构造
     *
     * @param logPublisher 事件发布器
     */
    public LogInterceptor(LogPublisher logPublisher) {
        this.publisher = logPublisher;
    }

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
        LogBeforeEvent beforeEvent = new LogBeforeEvent(invocation);
        LogAfterThrowingEvent afterThrowingEvent = new LogAfterThrowingEvent(invocation);
        LogAfterEvent afterEvent = new LogAfterEvent(invocation);
        setBaseInfo(method, clazzLog, methodLog, beforeEvent, afterThrowingEvent, afterEvent);
        setBeforeAware(clazzLog, methodLog, invocation.getArguments(), beforeEvent, afterThrowingEvent, afterEvent);
        publisher.before(beforeEvent);
        Object proceed = null;
        try {
            proceed = invocation.proceed();
        } catch (Throwable throwable) {
            setAfterThrowingAware(throwable, afterThrowingEvent, afterEvent);
            publisher.afterThrowing(afterThrowingEvent);
            throw throwable;
        } finally {
            setAfterAware(clazzLog, methodLog, proceed, afterEvent);
            publisher.after(afterEvent);
        }
        return proceed;
    }

    /**
     * 设置基本信息
     *
     * @param method
     * @param clazzLog
     * @param methodLog
     * @param aware
     */
    private void setBaseInfo(Method method, Log clazzLog, Log methodLog, LogEvent... aware) {
        for (LogEvent logEvent : aware) {
            logEvent.setBatch(IdUtil.fastSimpleUUID());
            if (null != methodLog) {
                if (StrUtil.isBlank(methodLog.function()) && null != clazzLog) {
                    logEvent.setFunction(clazzLog.function());
                } else {
                    logEvent.setFunction(methodLog.function());
                }
                if (StrUtil.isBlank(methodLog.action()) && null != clazzLog) {
                    logEvent.setAction(clazzLog.action());
                } else {
                    logEvent.setAction(methodLog.action());
                }
                if (StrUtil.isBlank(methodLog.description()) && null != clazzLog) {
                    logEvent.setDescription(clazzLog.description());
                } else {
                    logEvent.setDescription(methodLog.description());
                }
            } else {
                logEvent.setFunction(clazzLog.function());
                logEvent.setAction(clazzLog.action());
                logEvent.setDescription(clazzLog.description());
            }
            logEvent.setClazz(method.getDeclaringClass());
            logEvent.setMethod(method);
        }
    }

    /**
     * 设置请求信息
     *
     * @param clazzLog
     * @param methodLog
     * @param args
     * @param as
     */
    private void setBeforeAware(Log clazzLog, Log methodLog, Object[] args, LogBeforeAware... as) {
        for (LogBeforeAware aware : as) {
            aware.setRequestTime(LocalDateTime.now());
            if (null != methodLog ? methodLog.request() : clazzLog.request()) {
                aware.setRequest(args);
            }
            aware.setUrl(RequestHolder.getUrl());
            aware.setHost(RequestHolder.getHost());
            aware.setUserId(null == UserHolder.getUserId() ? null : UserHolder.getUserId().toString());
        }
    }

    /**
     * 设置异常事件信息
     *
     * @param throwable
     * @param as
     */
    private void setAfterThrowingAware(Throwable throwable, LogAfterThrowingAware... as) {
        for (LogAfterThrowingAware aware : as) {
            aware.setThrowable(throwable);
            aware.setSuccess(false);
        }
    }

    /**
     * 设置响应结果
     *
     * @param clazzLog
     * @param methodLog
     * @param proceed
     * @param as
     */
    private void setAfterAware(Log clazzLog, Log methodLog, Object proceed, LogAfterAware... as) {
        for (LogAfterAware aware : as) {
            if (null == aware.getSuccess()) {
                aware.setSuccess(true);
            }
            aware.setResponseTime(LocalDateTime.now());
            if (null != methodLog ? methodLog.response() : clazzLog.response()) {
                aware.setResponse(proceed);
            }
        }
    }
}
