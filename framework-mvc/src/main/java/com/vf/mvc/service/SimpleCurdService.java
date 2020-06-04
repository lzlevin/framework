package com.vf.mvc.service;

/**
 * 简单curd服务，不设置DTO、PO、Entity之间的转换
 *
 * @author levin
 * @since 1.0.0
 */
public interface SimpleCurdService<E> extends CurdService<E, E, E> {
    /**
     * 创建entity
     *
     * @param e DTO转化为entity
     * @return entity
     */
    @Override
    default E createEntity(E e) {
        return e;
    }

    /**
     * 创建PO
     *
     * @param entity
     * @return
     */
    @Override
    default E createPO(E entity) {
        return entity;
    }
}
