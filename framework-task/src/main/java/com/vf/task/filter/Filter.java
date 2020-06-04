package com.vf.task.filter;

/**
 * 过滤器
 *
 * @author levin
 * @date 2020/5/25
 * @since 1.0.0
 */
public interface Filter<T> {
    /**
     * 执行过滤
     *
     * @param target 源数据
     * @return 是否过滤
     */
    boolean accept(T target);
}
