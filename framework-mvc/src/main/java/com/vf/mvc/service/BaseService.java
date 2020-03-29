package com.vf.mvc.service;

import com.vf.mybatis.entity.BaseEntity;
import com.vf.mybatis.mapper.BaseMapper;
import com.vf.mybatis.service.impl.ServiceImpl;

/**
 * @author levin
 * @since 1.0.0
 */
public interface BaseService<E extends BaseEntity<Long>> {

    <D extends ServiceImpl<BaseMapper<E>, E, Long>> D getDao();

}
