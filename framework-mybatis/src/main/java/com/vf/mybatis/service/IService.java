package com.vf.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.Collection;


/**
 * 基础服务，扩展{@link com.baomidou.mybatisplus.extension.service.IService}
 *
 * @author levin
 * @since 1.0.0
 */
public interface IService<T> extends com.baomidou.mybatisplus.extension.service.IService<T> {
    /**
     * 批量插入（使用数据库提供支持）
     *
     * @param collection 插入的数据
     * @return 插入条数
     */
    int saveInBatch(Collection<T> collection);

    /**
     * 根据 Wrapper 条件，查询最大记录值
     *
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    Object max(Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询最小记录值
     *
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    Object min(Wrapper<T> queryWrapper);

}
