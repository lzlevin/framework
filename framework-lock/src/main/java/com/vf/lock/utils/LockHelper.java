package com.vf.lock.utils;

import cn.hutool.core.util.StrUtil;
import com.vf.lock.config.LockConfig;
import com.vf.lock.model.LockException;
import com.vf.lock.model.LockType;
import com.vf.utils.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;

import java.util.concurrent.TimeUnit;

/**
 * Redisson 锁 Helper，提供多种功能
 *
 * @author levin
 * @date 2020/6/13
 * @since 1.0.0
 */
@Slf4j
public final class LockHelper implements BeanFactoryAware, SmartInitializingSingleton {

    private RedissonClient redissonClient;
    private BeanFactory beanFactory;

    private final LockConfig lockConfig;

    public LockHelper(LockConfig lockConfig) {
        this.lockConfig = lockConfig;
    }


    /**
     * 加锁（可重入锁）并执行操作
     *
     * @param name     锁
     * @param supplier 执行操作
     * @param <T>      返回结果
     * @param lockType 锁类型
     * @return 返回结果
     */
    public <T> T tryLock(String name, LockType lockType, int waitTime, int leaseTime, Supplier<T> supplier, String message) throws Throwable {
        Assert.isNull(redissonClient, "supplier must be not null");
        if (StrUtil.isBlank(message)) {
            message = LockException.DEFAULT_MESSAGE;
        }
        RLock lock = null;
        try {
            log.info("get lock {}", name);
            lock = getLock(name, lockType);
            boolean locked = lock.tryLock(resolveTime(waitTime), resolveTime(leaseTime), TimeUnit.SECONDS);
            if (!locked) {
                throw new LockException(message);
            }
            return supplier.get();
        } catch (InterruptedException e) {
            throw new LockException(message, e);
        } finally {
            if (null != lock && lock.isHeldByCurrentThread()) {
                log.info("release lock {}", name);
                lock.unlock();
            }
        }
    }

    /**
     * 根据锁名称以及锁类型创建锁
     *
     * @param name     锁名称
     * @param lockType 锁类型
     * @return
     */
    public RLock getLock(String name, LockType lockType) {
        Assert.isBlank(name, "name must be not null");
        if (null == lockType) {
            lockType = LockType.Reentrant;
        }
        name = LockConfig.DEFAULT_LOCK_NAME_PREFIX + name;
        switch (lockType) {
            case Fair:
                return redissonClient.getFairLock(name);
            case Read:
                return redissonClient.getReadWriteLock(name).readLock();
            case Write:
                return redissonClient.getReadWriteLock(name).writeLock();
            default:
                return redissonClient.getLock(name);
        }
    }

    /**
     * 解释时间，将小于0的时间替换为默认配置时间
     *
     * @param time 时间
     * @return 时间
     */
    public int resolveTime(int time) {
        return time < 0 ? lockConfig.getWaitTime() : time;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterSingletonsInstantiated() {
        RedissonClient bean = beanFactory.getBean(RedissonClient.class);
        this.redissonClient = bean;
    }
}
