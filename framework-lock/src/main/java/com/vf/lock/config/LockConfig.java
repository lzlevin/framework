package com.vf.lock.config;

/**
 * @author levin
 * @date 2020/6/14
 * @since 1.0.0
 */
public final class LockConfig {
    /**
     * 默认锁前缀
     */
    public static final String DEFAULT_LOCK_NAME_PREFIX = "vfLock:";
    /**
     * 默认等待时间为30秒
     */
    public static final int DEFAULT_WAIT_TIME = 30;
    /**
     * 默认释放时间为30秒
     */
    public static final int DEFAULT_LEASE_TIME = 30;

    private int waitTime = DEFAULT_WAIT_TIME;

    private int leaseTime = DEFAULT_LEASE_TIME;

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        if (waitTime < 0) {
            throw new RuntimeException("wait time must be greater than 0");
        }
        this.waitTime = waitTime;
    }

    public int getLeaseTime() {
        return leaseTime;
    }

    public void setLeaseTime(int leaseTime) {
        if (waitTime < 0) {
            throw new RuntimeException("lease time must be greater than 0");
        }
        this.leaseTime = leaseTime;
    }
}
