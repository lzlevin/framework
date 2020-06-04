package com.vf.log.event;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.EventObject;

/**
 * 日志信息
 *
 * @author levin
 * @since 1.0.0
 */
@Getter
@Setter
public abstract class LogEvent extends EventObject {

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
    protected String batch;
    /**
     * 功能（模块）
     */
    protected String function;
    /**
     * 操作
     */
    protected String action;
    /**
     * 描述信息
     */
    protected String description;
    /**
     * 类
     */
    protected Class<?> clazz;
    /**
     * 方法
     */
    protected Method method;

    /**
     * 获取简单的描述
     *
     * @return
     */
    public StringBuilder getSimpleDesc() {
        StringBuilder sb = new StringBuilder("[");
        if (null != clazz) {
            sb.append(clazz.getSimpleName());
            if (null != method) {
                sb.append("#");
            }
        }
        if (null != method) {
            sb.append(method.getName());
        }
        sb.append(",");
        if (null != function) {
            sb.append(function);
            if (null != action) {
                sb.append(":");
            }
        }
        if (null != action) {
            sb.append(action);
        }
        sb.append("]");
        return sb;
    }
}
