package com.vf.mvc.service;

import com.vf.log.annotation.Log;
import com.vf.mybatis.entity.BaseEntity;

import java.util.Collection;

/**
 * 基础更新服务
 *
 * @author levin
 * @since 1.0.0
 */
public interface UpdateService<E extends BaseEntity<Long>> extends BaseService<E> {
    /**
     * 根据ID更新
     *
     * @param entity 更新的数据
     * @return 是否更新成功
     */
    @Log(action = "更新")
    default boolean updateById(E entity) {
        return getDao().updateById(entity);
    }

    /**
     * 批量更新
     *
     * @param list 更新数据list
     * @return 更新成功
     */
    @Log(action = "批量更新")
    default boolean updateBatchById(Collection<E> list) {
        return getDao().updateBatchById(list);
    }
}
