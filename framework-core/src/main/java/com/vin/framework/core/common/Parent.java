package com.vin.framework.core.common;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 具有父子节点ID
 *
 * @param <K> ID类型
 */
public interface Parent<K> extends Idable<K> {

    /**
     * 默认string类型的根节点标识
     */
    String DEFAULT_STRING_ROOT = "ROOT";
    /**
     * 默认long类型的根节点标识
     */
    Long DEFAULT_LONG_ROOT = -1L;

    /**
     * 父节点ID
     *
     * @return 父节点ID
     */
    K getParentId();

    /**
     * 设置孩子节点
     *
     * @param list
     */
    default void setChildren(List<? extends Parent<K>> list) {

    }

    /**
     * 将list转化为树形结构
     *
     * @param list 要转化的list
     * @param <T>  list的元素类型
     * @return
     */
    static <T extends Parent<Long>> List<T> treeLong(List<T> list) {
        return tree(list, DEFAULT_LONG_ROOT);
    }

    /**
     * 将list转化为树形结构
     *
     * @param list 要转化的list
     * @param <T>  list的元素类型
     * @return
     */
    static <T extends Parent<String>> List<T> treeString(List<T> list) {
        return tree(list, DEFAULT_STRING_ROOT);
    }

    /**
     * 将list转化为树形结构
     *
     * @param list 要转化的list
     * @param root 根节点标识
     * @param <K>  根节点类型
     * @param <T>  list的元素类型
     * @return
     */
    static <K, T extends Parent<K>> List<T> tree(List<T> list, K root) {
        Map<K, List<T>> collect = list.stream().collect(Collectors.groupingBy(Parent::getParentId));
        List<T> ts = collect.get(root);
        if (null == ts || ts.size() == 0) {
            throw new RuntimeException("根节点不存在");
        }
        list.forEach(
                t -> t.setChildren(collect.get(t.getId()))
        );
        return ts;
    }
}
