package com.vf.lock.utils;

/**
 * @author levin
 * @date 2020/6/14
 * @since 1.0.0
 */
public interface Supplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get() throws Throwable;
}
