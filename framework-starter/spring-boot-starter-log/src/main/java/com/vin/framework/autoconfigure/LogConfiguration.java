package com.vin.framework.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 日志配置
 *
 * @author levin
 * @since 1.0.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "vin.log")
public class LogConfiguration {
    /**
     * 默认线程超时时间
     */
    public static final int DEFAULT_TIMEOUT = 5;
    /**
     * 默认最大线程
     */
    public static final int DEFAULT_MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    /**
     * 默认核心线程
     */
    public static final int DEFAULT_CORE_POOL_SIZE = 2;

    /**
     * 日志超时时间
     */
    private int timeout = DEFAULT_TIMEOUT;
    /**
     * 最大线程
     */
    private int maxPoolSize = DEFAULT_MAX_POOL_SIZE;
    /**
     * 核心线程数
     */
    private int corePoolSize = DEFAULT_CORE_POOL_SIZE;
    /**
     * 启用控制台
     */
    private boolean stdout = false;
    /**
     * 启用slf4j
     */
    private boolean slf4j = true;
    /**
     * 启用数据库
     */
    private boolean database = true;

}
