package com.vin.framework.core.entity;

import java.util.Collection;

/**
 * 具有父节点性质的
 *
 * @author levin
 * @since 1.0.0
 */
public interface Paternal<T> {

    /**
     * 获取父亲
     *
     * @return 父亲
     */
    T getParent();

    /**
     * 获取孩子
     *
     * @return 孩子
     */
    Collection<T> getChildren();
}
