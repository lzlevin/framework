package com.vf.lock.aop;

import com.vf.lock.annotation.Lock;
import com.vf.lock.utils.LockHelper;
import com.vf.utils.lang.Assert;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * {@link Lock}注解切面的通知
 *
 * @author levin
 * @date 2020/6/13
 * @since 1.0.0
 */
public class LockAnnotationAdvice implements MethodInterceptor {

    private LockKeyResolver lockKeyResolver = new LockKeyResolver();

    private LockHelper lockHelper;

    public LockAnnotationAdvice(LockHelper lockHelper) {
        Assert.isNull(lockHelper, "lockHelper must be not null");
        this.lockHelper = lockHelper;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Lock annotation = invocation.getMethod().getAnnotation(Lock.class);
        String key = lockKeyResolver.resolveKey(invocation.getMethod(), invocation.getArguments());
        return lockHelper.tryLock(key, annotation.lockType(), annotation.waitTime(), annotation.leaseTime(), () -> invocation.proceed());
    }
}
