package com.vf.common.entity;

/**
 * 具有孩子属性的
 *
 * @author levin
 * @date 2020/4/30
 * @since 1.0.0
 */
public interface ChildrenAware<C> {
    /**
     * 设置孩子节点
     */
    void setChildren(Iterable<C> children);
}
