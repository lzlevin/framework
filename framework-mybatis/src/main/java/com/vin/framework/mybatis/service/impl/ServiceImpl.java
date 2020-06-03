package com.vin.framework.mybatis.service.impl;

import com.vin.framework.core.entity.BaseEntity;
import com.vin.framework.mybatis.mapper.BaseMapper;
import com.vin.framework.mybatis.service.IService;

import java.util.Collection;

/**
 * 基础业务，扩展{@link com.baomidou.mybatisplus.extension.service.IService}
 *
 * @author levin
 * @since 1.0.0
 */
public class ServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<M, T> implements IService<T> {
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
}