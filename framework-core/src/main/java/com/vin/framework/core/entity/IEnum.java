package com.vin.framework.core.entity;

import com.vin.framework.core.exception.BusinessException;

import java.util.Objects;

/**
 * key value形式通用枚举类
 * @author levin
 * @since 1.0.0
 */
public interface IEnum<K, V> extends KeyValuable<K, V> {

    /**
     * 获取枚举的描述信息
     * @param clazz 枚举类
     * @param key key键
     * @param <K> key键类型
     * @param <V> value键类型
     * @param <T> 枚举
     * @return 如果存在则返回对应的value, 不存在则返回null
     */
    static <K, V, T extends Enum & IEnum<K, V>> V getKey(Class<T> clazz, K key) {
        return getKey(clazz, key, false);
    }

    /**
     * 获取枚举的描述信息
     * @param clazz 枚举类
     * @param key key键
     * @param th   是否抛出异常，是则找不到相应的值则抛出异常
     * @param <K> key键类型
     * @param <V> value键类型
     * @param <T> 枚举
     * @return 如果存在则返回对应的value, 不存在则根据{@code th}参数抛出异常或者返回null
     */
    static <K, V, T extends Enum & IEnum<K, V>> V getKey(Class<T> clazz, K key, boolean th) {
        T[] enumConstants = clazz.getEnumConstants();
        for (T enumConstant : enumConstants) {
            if (Objects.equals(enumConstant.getKey(), key)) {
                return enumConstant.getValue();
            }
        }
        if (th) {
            throw new BusinessException("未找到字典值");
        } else {
            return null;
        }
    }

    /**
     * 获取枚举的描述信息
     * @param clazz 枚举类
     * @param key key键
     * @param <K> key键类型
     * @param <V> value键类型
     * @param <T> 枚举
     * @return 存在则返回true，否则返回false
     */
    static <K, V, T extends Enum & IEnum<K, V>> boolean exist(Class<T> clazz, K key) {
        T[] enumConstants = clazz.getEnumConstants();
        for (T enumConstant : enumConstants) {
            if (Objects.equals(enumConstant.getKey(), key)) {
                return true;
            }
        }
        return false;
    }
}
