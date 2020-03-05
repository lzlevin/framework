package com.vin.framework.utils.bean;

import java.util.List;
import java.util.stream.Collectors;

/**
 * bean工具类，扩展{@link cn.hutool.core.bean.BeanUtil}提供更多的方法
 *
 * @author levin
 * @since 1.0.0
 */
public class BeanUtil extends cn.hutool.core.bean.BeanUtil {
    /**
     * 拷贝list集合
     *
     * @param list  list集合
     * @param clazz 要转换的类型
     * @param <A>   原始类型
     * @param <B>   要转换的类型
     * @return 转换后类型的集合
     */
    public static <A, B> List<B> copyPropertiesList(List<A> list, Class<B> clazz) {
        if (null == clazz) {
            throw new IllegalArgumentException("clazz不能为空");
        }
        if (null == list) {
            throw new IllegalArgumentException("list不能为空");
        }
        return list.stream().map(t -> copyPropertiesClazz(t, clazz)).collect(Collectors.toList());
    }

    /**
     * 通过反射实例化对象并拷贝属性
     *
     * @param source 源对象
     * @param clazz  目标对象class
     * @param <A>    原对象类型
     * @param <B>    目标对象类型
     * @return 目标类型
     */
    public static <A, B> B copyPropertiesClazz(A source, Class<B> clazz) {
        if (null == clazz) {
            throw new IllegalArgumentException("clazz不能为空");
        }
        if (null == source) {
            throw new IllegalArgumentException("source不能为空");
        }
        B object = null;
        try {
            object = clazz.newInstance();
            copyProperties(source, object);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(clazz.getName() + "不可实例化");
        }
        return object;
    }
}
