package com.vf.mvc.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vf.log.annotation.Log;
import com.vf.mybatis.entity.BaseEntity;

/**
 * 基础分页查询服务
 *
 * @author levin
 * @since 1.0.0
 */
public interface RetrievePageService<E extends BaseEntity<Long>> extends BaseService<E> {

    /**
     * 根据条件分页查询
     *
     * @param page   分页参数
     * @param entity 查询条件
     * @param <P>    分页实体
     * @return 分页查询结果
     */
    @Log(action = "根据条件分页查询")
    default <P extends IPage<E>> P page(P page, E entity) {
        return getDao().page(page, Wrappers.query(entity));
    }

    /**
     * 根据条件分页查询
     *
     * @param page    分页参数
     * @param wrapper 查询条件
     * @param <P>     分页实体
     * @return 分页查询结果
     */
    @Log(action = "根据条件分页查询")
    default <P extends IPage<E>> P page(P page, Wrapper<E> wrapper) {
        return getDao().page(page, wrapper);
    }
}
