package com.vf.log.listener;

import com.vf.log.event.LogAfterEvent;
import com.vf.log.event.LogAfterThrowingEvent;
import com.vf.log.event.LogBeforeEvent;

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
    default void before(LogBeforeEvent event) {

    }

    /**
     * 执行异常的日志记录
     *
     * @param event 日志记录
     */
    default void afterThrowing(LogAfterThrowingEvent event) {

    }

    /**
     * 执行完毕后的日志记录
     *
     * @param event
     */
    void after(LogAfterEvent event);

    /**
     * 获取顺序，顺序越小越先执行
     *
     * @return 顺序，用于触发次序
     */
    default int getOrder() {
        return Integer.MIN_VALUE;
    }

    /**
     * 是否异步
     *
     * @return 是否异步
     */
    default boolean isAsync() {
        return false;
    }
}
