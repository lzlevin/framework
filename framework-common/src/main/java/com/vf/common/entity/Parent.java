package com.vf.common.entity;

/**
 * 具有父子节点ID
 *
 * @param <K> ID类型
 */
public interface Parent<K> extends Idable<K> {
    /**
     * 父节点ID
     *
     * @return 父节点ID
     */
    K getParentId();

    /**
     * 设置父ID
     *
     * @param id 父ID
     */
    void setParentId(K id);
}
