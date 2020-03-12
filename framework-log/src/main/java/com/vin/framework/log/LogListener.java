package com.vin.framework.log;

import java.util.EventListener;

/**
 * 日志事件监听器
 *
 * @author levin
 * @since 1.0.0
 */
public interface LogListener extends EventListener {

    /**
     * 执行前的日志记录
     *
     * @param event 日志事件
     */
    default void before(LogEvent event) {

    }

    /**
     * 执行异常的日志记录
     *
     * @param event 日志记录
     */
    default void afterThrowing(LogEvent event) {

    }

    /**
     * 执行完毕后的日志记录
     *
     * @param event
     */
    void after(LogEvent event);
}
