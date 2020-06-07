package com.vf.common.entity;

/**
 * 启用标识
 *
 * @author levin
 * @since 1.0.0
 */
public interface UseFlag {
    /**
     * 启用
     */
    Boolean ENABLE = true;
    /**
     * 停用
     */
    Boolean DISABLE = false;

    /**
     * 获取启用标识
     *
     * @return 启用标识
     */
    Boolean getUseFlag();

    /**
     * 设置启用标识
     */
    void setUseFlag(Boolean useFlag);
}
