package com.vin.framework.core.entity;

import java.util.List;

/**
 * 具有父子节点ID
 * @param <K> ID类型
 */
public interface Parent<K> {

    /**
     * ID
     * @return 节点ID
     */
    K getId();

    /**
     * 父节点ID
     * @return 父节点ID
     */
    K getParentId();

    /**
     * 设置孩子节点
     * @param list
     */
    default void setChildren(List<? extends Parent<K>> list) {

    }
}
