package com.vf.log.event;

import com.vf.log.listener.LogListener;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 参见{@link LogEvent}和{@link LogListener}
 *
 * @author levin
 * @since 1.0.0
 */
@Getter
@Setter
public class LogAfterEvent extends LogEvent implements LogAfterAware, LogBeforeAware, LogAfterThrowingAware {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public LogAfterEvent(Object source) {
        super(source);
    }

    /**
     * 请求数据
     */
    protected Object request;
    /**
     * 请求时间
     */
    protected LocalDateTime requestTime;

    /**
     * 请求url（http等来说）
     */
    protected String url;
    /**
     * 请求主机
     */
    protected String host;
    /**
     * 用户ID
     */
    protected String userId;

    /**
     * 异常
     */
    protected Throwable throwable;
    /**
     * 结果
     */
    protected Boolean success;
    /**
     * 响应数据
     */
    private Object response;
    /**
     * 响应时间
     */
    private LocalDateTime responseTime;

    @Override
    public String toString() {
        return getSimpleDesc().append(",finish===========<").toString();
    }

}