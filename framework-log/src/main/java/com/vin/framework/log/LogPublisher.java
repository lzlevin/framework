package com.vin.framework.log;

import java.util.ArrayList;
import java.util.List;

/**
 * 日志发布器
 *
 * @author levin
 * @since 1.0.0
 */
public class LogPublisher implements LogListener {

    /**
     * 监听器列表
     */
    private final List<LogListener> listeners = new ArrayList<>();

    /**
     * 添加监听器
     *
     * @param logListener 监听器
     * @return 发布器
     */
    public LogPublisher addListener(LogListener logListener) {
        if (null == logListener) {
            throw new IllegalArgumentException("logListener could not be null");
        }
        if (!listeners.contains(logListener)) {
            listeners.add(logListener);
        }
        return this;
    }

    /**
     * 执行完毕后的日志记录
     *
     * @param event
     */
    @Override
    public void after(LogEvent event) {
        listeners.forEach(t -> t.after(event));
    }

    /**
     * 执行前的日志记录
     *
     * @param event 日志事件
     */
    @Override
    public void before(LogEvent event) {
        listeners.forEach(t -> t.before(event));
    }

    /**
     * 执行异常的日志记录
     *
     * @param event 日志记录
     */
    @Override
    public void afterThrowing(LogEvent event) {
        listeners.forEach(t -> t.afterThrowing(event));
    }
}
