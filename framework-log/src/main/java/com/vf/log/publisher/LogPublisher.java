package com.vf.log.publisher;

import com.vf.log.event.LogAfterEvent;
import com.vf.log.event.LogAfterThrowingEvent;
import com.vf.log.event.LogBeforeEvent;
import com.vf.log.listener.LogListener;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 日志发布器
 *
 * @author levin
 * @since 1.0.0
 */
public interface LogPublisher extends LogListener {
    /**
     * 添加监听器
     *
     * @param logListener 监听器
     * @return 发布器
     */
    LogPublisher addListener(LogListener logListener);

    /**
     * 移除监听器
     *
     * @param logListener 监听器
     * @return 发布器
     */
    LogPublisher removeListener(LogListener logListener);

    /**
     * 获取监听器列表
     *
     * @return 监听器列表
     */
    List<LogListener> getListeners();

    /**
     * 获取异步监听器
     *
     * @return 监听器
     */
    default List<LogListener> getAsyncListeners() {
        return getListeners().stream().filter(LogListener::isAsync).collect(Collectors.toList());
    }

    /**
     * 获取同步监听器
     *
     * @return 同步监听器
     */
    default List<LogListener> getSyncListeners() {
        return getListeners().stream().filter(t -> !t.isAsync())
                .sorted(Comparator.comparingInt(LogListener::getOrder))
                .collect(Collectors.toList());
    }

    /**
     * 执行完毕后的日志记录
     *
     * @param event
     */
    @Override
    default void after(LogAfterEvent event) {
        getListeners().forEach(t -> t.after(event));
    }

    /**
     * 执行前的日志记录
     *
     * @param event 日志事件
     */
    @Override
    default void before(LogBeforeEvent event) {
        getListeners().forEach(t -> t.before(event));
    }

    /**
     * 执行异常的日志记录
     *
     * @param event 日志记录
     */
    @Override
    default void afterThrowing(LogAfterThrowingEvent event) {
        getListeners().forEach(t -> t.afterThrowing(event));
    }
}
