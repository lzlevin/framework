package com.vf.lock.model;

/**
 * @author levin
 * @date 2020/6/13
 * @since 1.0.0
 */
public class LockException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "业务繁忙，请稍后再试";

    public LockException() {
        this(DEFAULT_MESSAGE);
    }

    public LockException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockException(String message) {
        super(message);
    }

    public LockException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}
