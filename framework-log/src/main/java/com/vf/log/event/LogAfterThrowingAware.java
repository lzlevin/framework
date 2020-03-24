package com.vf.log.event;

/**
 * @author levin
 * @since 1.0.0
 */
public interface LogAfterThrowingAware {

    void setThrowable(Throwable throwable);

    void setSuccess(Boolean success);
}
