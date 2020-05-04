package com.vf.common.util;

import cn.hutool.core.convert.Convert;
import com.vf.common.entity.ChildrenAware;
import com.vf.common.entity.Parent;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author levin
 * @date 2020/4/30
 * @since 1.0.0
 */
public class TreeUtils {

    /**
     * 默认string类型的根节点标识
     */
    public static final String DEFAULT_ROOT = "-1";

    /**
     * 默认long类型的根节点
     */
    public static final Long DEFAULT_LONG_ROOT = -1L;

    /**
     * 默认叶子节点深度
     */
    public static final Integer DEFAULT_LEAF_DEPTH = 1;

    /**
     * 将list转化为树形结构
     *
     * @param list  list集合
     * @param clazz 字段类型
     * @param <K>   字段类型
     * @param <T>   实体类型
     * @return
     */
    public static <K, T extends Parent<K> & ChildrenAware> List<T> tree(List<T> list, Class<K> clazz) {
        return tree(list, Convert.convert(clazz, DEFAULT_ROOT));
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
    public static <K, T extends Parent<K> & ChildrenAware> List<T> tree(List<T> list, K root) {
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
