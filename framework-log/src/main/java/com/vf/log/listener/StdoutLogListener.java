package com.vf.log.listener;

import com.vf.log.event.LogAfterEvent;
import com.vf.log.event.LogAfterThrowingEvent;
import com.vf.log.event.LogBeforeEvent;


/**
 * {@link System#out}的监听器
 *
 * @author levin
 * @see System#out
 * @since 1.0.0
 */
public class StdoutLogListener implements LogListener {
    /**
     * 执行前的日志记录
     *
     * @param event 日志事件
     */
    @Override
    public void before(LogBeforeEvent event) {
        System.out.println(event.toString());
    }

    /**
     * 执行异常的日志记录
     *
     * @param event 日志记录
     */
    @Override
    public void afterThrowing(LogAfterThrowingEvent event) {
        event.getThrowable().printStackTrace(System.err);
    }

    /**
     * 执行完毕后的日志记录
     *
     * @param event
     */
    @Override
    public void after(LogAfterEvent event) {
        System.out.println(event.toString());
    }
}
