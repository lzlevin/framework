package com.vf.mvc.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vf.log.annotation.Log;
import com.vf.mybatis.entity.BaseEntity;

import java.util.List;

/**
 * 基础查询服务
 *
 * @author levin
 * @since 1.0.0
 */
public interface RetrieveService<E extends BaseEntity<Long>> extends BaseService<E> {
    /**
     * 根据ID查询
     *
     * @param id 数据ID
     * @return 查询到的数据
     */
    @Log(action = "根据ID查询")
    default E getById(Long id) {
        return getDao().getById(id);
    }

    /**
     * 根据条件查询
     *
     * @param entity 查询条件
     * @return 查询结果
     */
    @Log(action = "根据条件查询")
    default List<E> list(E entity) {
        return getDao().list(Wrappers.query(entity));
    }

    /**
     * 根据条件查询
     *
     * @param wrapper 查询条件
     * @return 查询结果
     */
    @Log(action = "根据条件查询")
    default List<E> list(Wrapper<E> wrapper) {
        return getDao().list(wrapper);
    }
}
