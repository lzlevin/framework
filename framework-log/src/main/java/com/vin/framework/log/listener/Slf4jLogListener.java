package com.vin.framework.log.listener;

import com.vin.framework.log.event.LogAfterEvent;
import com.vin.framework.log.event.LogAfterThrowingEvent;
import com.vin.framework.log.event.LogBeforeEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link org.slf4j.Logger}的监听器
 *
 * @author levin
 * @since 1.0.0
 */
@Slf4j
public class Slf4jLogListener implements LogListener {
    /**
     * 执行前的日志记录
     *
     * @param event 日志事件
     */
    @Override
    public void before(LogBeforeEvent event) {
        log.info(event.toString());
    }

    /**
     * 执行异常的日志记录
     *
     * @param event 日志记录
     */
    @Override
    public void afterThrowing(LogAfterThrowingEvent event) {
        log.error(event.toString(), event.getThrowable());
    }

    /**
     * 执行完毕后的日志记录
     *
     * @param event
     */
    @Override
    public void after(LogAfterEvent event) {
        log.info(event.toString());
    }

    /**
     * 获取顺序，顺序越大越先执行
     *
     * @return 顺序，用于触发次序
     */
    @Override
    public int getOrder() {
        return Integer.MIN_VALUE + 10;
    }
}
