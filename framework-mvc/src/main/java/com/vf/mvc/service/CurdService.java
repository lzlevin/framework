package com.vf.mvc.service;

import com.vf.mybatis.entity.BaseEntity;

/**
 * 基础curd服务
 *
 * @author levin
 * @since 1.0.0
 */
public interface CurdService<E extends BaseEntity<Long>> extends BaseService<E>,
        CreateService<E>, RetrieveService<E>, RetrievePageService<E>, UpdateService<E>, DeleteService<E> {
}
