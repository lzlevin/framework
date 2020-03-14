package com.vin.framework.log.publisher;

import com.vin.framework.log.listener.LogListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 日志发布器
 *
 * @author levin
 * @since 1.0.0
 */
public class SimpleLogPublisher implements LogPublisher {

    /**
     * 监听器列表
     */
    private final List<LogListener> listeners = new ArrayList<>();

    /**
     * 获取监听器列表
     *
     * @return 监听器列表
     */
    @Override
    public List<LogListener> getListeners() {
        return listeners;
    }

    /**
     * 添加监听器
     *
     * @param logListener 监听器
     * @return 发布器
     */
    @Override
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
     * 移除监听器
     *
     * @param logListener 监听器
     * @return 发布器
     */
    @Override
    public LogPublisher removeListener(LogListener logListener) {
        if (null == logListener) {
            throw new IllegalArgumentException("logListener could not be null");
        }
        if (listeners.contains(logListener)) {
            listeners.remove(logListener);
        }
        return this;
    }

}
