package com.vin.framework.log.event;

import java.time.LocalDateTime;

/**
 * @author levin
 * @since 1.0.0
 */
public interface LogBeforeAware {

    void setRequest(Object request);

    void setRequestTime(LocalDateTime requestTime);

    void setUrl(String url);

    void setHost(String host);

    void setUserId(String userId);
}
