package com.vf.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vf.mybatis.mapper.BaseMapper;
import com.vf.mybatis.service.IService;

import java.util.Collection;

/**
 * 基础业务，扩展{@link com.baomidou.mybatisplus.extension.service.IService}
 *
 * @author levin
 * @since 1.0.0
 */
public abstract class ServiceImpl<M extends BaseMapper<T>, T> extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<M, T> implements IService<T> {
    /**
     * 批量插入（使用数据库提供支持）
     *
     * @param collection 插入的数据
     * @return 插入条数
     */
    @Override
    public int saveInBatch(Collection<T> collection) {
        return baseMapper.insertInBatch(collection);
    }


    /**
     * 根据 Wrapper 条件，查询最大记录值
     *
     * @param queryWrapper 实体对象封装操作类 {@link QueryWrapper}
     */
    @Override
    public Object max(Wrapper<T> queryWrapper) {
        return baseMapper.max(queryWrapper);
    }

    /**
     * 根据 Wrapper 条件，查询最小记录值
     *
     * @param queryWrapper 实体对象封装操作类 {@link QueryWrapper}
     */
    @Override
    public Object min(Wrapper<T> queryWrapper) {
        return baseMapper.min(queryWrapper);
    }
}
