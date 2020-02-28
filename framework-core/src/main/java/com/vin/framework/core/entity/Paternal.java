package com.vin.framework.core.entity;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 具有父节点性质的
 *
 * @author levin
 * @since 1.0.0
 */
public interface Paternal<K, T extends Parent<K>> {

    String DEFUALT_STRING_ROOT = "ROOT";
    Integer DEFAULT_INTEGER_ROOT = -1;
    Long DEFAULT_LONG_ROOT = -1L;

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

    static <K, T extends Parent<K>> List<T> tree(List<T> list, K root) {
        Map<K, List<T>> collect = list.stream().collect(Collectors.groupingBy(Parent::getParentId));
        List<T> ts = collect.get(root);
        if (ts.size() == 0) {
            throw new RuntimeException("根节点不存在");
        }
        list.forEach(
                t -> t.setChildren(collect.get(t.getId()))
        );
        return ts;
    }
}
