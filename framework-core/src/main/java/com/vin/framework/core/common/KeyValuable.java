package com.vin.framework.core.common;

/**
 * key value形式结构
 * @author levin
 * @since 1.0.0
 */
public interface KeyValuable<K, V> {

    /**
     * 获取key
     * @return key
     */
    K getKey();

    /**
     * 获取value
     * @return value
     */
    V getValue();
}
