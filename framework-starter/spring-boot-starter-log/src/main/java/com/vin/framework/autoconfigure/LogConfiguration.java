package com.vin.framework.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.vin.framework.autoconfigure.LogConfiguration.DEFAULT_PREFIX;

/**
 * 日志配置
 *
 * @author levin
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = DEFAULT_PREFIX)
public class LogConfiguration {

    public static final String DEFAULT_PREFIX = "vin.log";
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
     * 默认控制台输出启用
     */
    public static final boolean DEFAULT_STDOUT_ENABLE = false;
    /**
     * 默认slf4j启用
     */
    public static final boolean DEFAULT_SLF4j_ENABLE = true;
    /**
     * 默认数据库是否启用
     */
    public static final boolean DEFAULT_DATABASE_ENABLE = false;

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
    private boolean stdout = DEFAULT_STDOUT_ENABLE;
    /**
     * 启用slf4j
     */
    private boolean slf4j = DEFAULT_SLF4j_ENABLE;
    /**
     * 启用数据库
     */
    private boolean database = DEFAULT_DATABASE_ENABLE;

}
