package com.vin.framework.log.event;

import java.time.LocalDateTime;

/**
 * @author levin
 * @since 1.0.0
 */
public interface LogAfterAware {

    void setResponse(Object response);

    void setResponseTime(LocalDateTime responseTime);

    Boolean getSuccess();

    void setSuccess(Boolean b);
}
