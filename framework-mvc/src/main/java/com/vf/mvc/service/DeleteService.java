package com.vf.mvc.service;

import com.vf.log.annotation.Log;
import com.vf.mybatis.entity.BaseEntity;

import java.util.Collection;

/**
 * 基础删除服务
 *
 * @author levin
 * @since 1.0.0
 */
public interface DeleteService<E extends BaseEntity<Long>> extends BaseService<E> {

    /**
     * 根据ID删除一条
     *
     * @param id 数据ID
     * @return 是否删除成功
     */
    @Log(action = "根据ID删除")
    default boolean removeById(Long id) {
        return getDao().removeById(id);
    }

    /**
     * 根据ID批量删除
     *
     * @param ids 批量ID
     * @return 是否删除成功
     */
    @Log(action = "根据ID批量删除")
    default boolean removeByIds(Collection<Long> ids) {
        return getDao().removeByIds(ids);
    }
}
