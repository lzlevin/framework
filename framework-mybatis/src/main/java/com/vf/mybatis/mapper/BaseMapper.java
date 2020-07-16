package com.vf.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Objects;

/**
 * @param <E> 实体类型
 * @author levin
 * @since 1.0.0
 */
public interface BaseMapper<E> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<E> {
    //TODO 因mybatis plus将是否更新空值（类似updateSelective/update功能）放置在配置中，考虑将此方法进行mapper扩展等

    /**
     * 插入所有数据所有
     *
     * @param iterable 插入的数据
     * @return 插入条数
     */
    default int insertAll(Collection<E> iterable) {
        if (Objects.isNull(iterable)) {
            return 0;
        }
        int count = 0;
        for (E e : iterable) {
            count += this.insert(e);
        }
        return count;
    }

    /**
     * 批量插入（使用数据库提供支持）
     *
     * @param iterable 插入的数据
     * @return 插入条数
     */
    int insertInBatch(Collection<E> iterable);

    /**
     * 根据 Wrapper 条件，最大值，根据queryWrapper中查询的第一列返回，查询多列不报错
     * E
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return 查询的列
     */
    Object max(@Param(Constants.WRAPPER) Wrapper<E> queryWrapper);

    /**
     * 根据 Wrapper 条件，最小值，根据queryWrapper中查询的第一列返回，查询多列不报错
     * E
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return 查询的列
     */
    Object min(@Param(Constants.WRAPPER) Wrapper<E> queryWrapper);
}
