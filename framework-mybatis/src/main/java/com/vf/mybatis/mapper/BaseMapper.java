package com.vf.mybatis.mapper;

import com.vf.core.entity.BaseEntity;

import java.util.Collection;
import java.util.Objects;

/**
 * @param <E> 实体类型
 * @author levin
 * @since 1.0.0
 */
public interface BaseMapper<E extends BaseEntity> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<E> {
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

}
