package com.vf.lock.aop;

import com.vf.lock.annotation.Lock;
import com.vf.lock.utils.LockHelper;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;

/**
 * {@link Lock}注解切面的通知
 *
 * @author levin
 * @date 2020/6/13
 * @since 1.0.0
 */
public class LockAnnotationAdvice implements MethodInterceptor, BeanFactoryAware, SmartInitializingSingleton {

    private LockKeyResolver lockKeyResolver = new LockKeyResolver();

    private BeanFactory beanFactory;

    private LockHelper lockHelper;

    private boolean initialized = false;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (initialized) {
            Lock annotation = invocation.getMethod().getAnnotation(Lock.class);
            String key = lockKeyResolver.resolveKey(invocation.getMethod(), invocation.getArguments());
            return lockHelper.tryLock(key, annotation.lockType(), annotation.waitTime(), annotation.leaseTime(), () -> invocation.proceed());
        } else {
            return invocation.proceed();
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.lockHelper = beanFactory.getBean(LockHelper.class);
        this.initialized = true;
    }
}
