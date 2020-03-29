package com.vf.common.entity;

/**
 * 可分页的
 *
 * @author levin
 * @since 1.0.0
 */
public interface Pageable {

    /**
     * 默认页面大小
     */
    Long DEFAULT_SIZE = 10L;
    /**
     * 默认当前页
     */
    Long DEFAULT_CURRENT = 1L;

    /**
     * 获取分页大小
     *
     * @return 分页大小
     */
    Long getSize();

    /**
     * 获取当前页
     *
     * @return 页码
     */
    Long getCurrent();

    /**
     * 获取总数
     *
     * @return 总数
     */
    default Long getTotal() {
        return 0L;
    }
}
