package com.vf.common.entity;

/**
 * 具有ID属性
 *
 * @author levin
 * @since 1.0.0
 */
public interface Idable<ID> {
    /**
     * 具有ID属性
     *
     * @return ID值
     */
    ID getId();
}
