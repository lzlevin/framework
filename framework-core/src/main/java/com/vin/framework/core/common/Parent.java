package com.vin.framework.core.common;

import cn.hutool.core.convert.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    String DEFAULT_STRING_ROOT = "-1";

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
     * @param list  list集合
     * @param clazz 字段类型
     * @param <K>   字段类型
     * @param <T>   实体类型
     * @return
     */
    static <K, T extends Parent<K>> List<T> tree(List<T> list, Class<K> clazz) {
        return tree(list, Convert.convert(clazz, DEFAULT_STRING_ROOT));
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
