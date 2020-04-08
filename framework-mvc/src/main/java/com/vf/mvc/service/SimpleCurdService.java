package com.vf.mvc.service;

/**
 * 简单curd服务，不设置DTO、PO、Entity之间的转换
 *
 * @author levin
 * @since 1.0.0
 */
public interface SimpleCurdService<E> extends CurdService<E, E, E> {
}
