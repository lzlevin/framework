package com.vin.framework.log;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.EventObject;

/**
 * 日志信息
 *
 * @author levin
 * @since 1.0.0
 */
@Getter
@Setter
@Accessors(chain = true)
public class LogEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public LogEvent(Object source) {
        super(source);
    }

    /**
     * 日志批次号，对于相同请求的不同事件定位相同的批次号方便查找及分析问题
     */
    private String batch;
    /**
     * 功能（模块）
     */
    private String function;
    /**
     * 操作
     */
    private String action;
    /**
     * 描述信息
     */
    private String description;
    /**
     * 类
     */
    private Class<?> clazz;
    /**
     * 方法
     */
    private Method method;
    /**
     * 请求数据
     */
    private Object request;
    /**
     * 响应数据
     */
    private Object response;
    /**
     * 请求时间
     */
    private LocalDateTime requestTime;
    /**
     * 响应时间
     */
    private LocalDateTime responseTime;
    /**
     * 结果
     */
    private Boolean success;
    /**
     * 异常
     */
    private Throwable throwable;
    /**
     * 请求url（http等来说）
     */
    private String url;
    /**
     * 请求主机
     */
    private String host;
    /**
     * 用户ID
     */
    private String userId;

    @Override
    public String toString() {
        return "LogEvent{" + clazz.getName() + ":" + method.getName() + "," + function + ":" + action + ":result:" + success;
    }
}
