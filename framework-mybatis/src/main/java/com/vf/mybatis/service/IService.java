package com.vf.mybatis.service;

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
}
