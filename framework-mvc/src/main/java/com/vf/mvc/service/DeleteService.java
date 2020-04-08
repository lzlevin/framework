package com.vf.mvc.service;

import com.vf.log.annotation.Log;

import java.io.Serializable;
import java.util.Collection;

/**
 * 基础删除服务
 *
 * @author levin
 * @since 1.0.0
 */
public interface DeleteService<E,DTO,PO> extends BaseService<E,DTO,PO> {

    /**
     * 根据ID删除一条
     *
     * @param id 数据ID
     * @return 是否删除成功
     */
    @Log(action = "根据ID删除")
    default boolean removeById(Serializable id) {
        return getDao().removeById(id);
    }

    /**
     * 根据ID批量删除
     *
     * @param ids 批量ID
     * @return 是否删除成功
     */
    @Log(action = "根据ID批量删除")
    default boolean removeByIds(Collection<Serializable> ids) {
        return getDao().removeByIds(ids);
    }
}
