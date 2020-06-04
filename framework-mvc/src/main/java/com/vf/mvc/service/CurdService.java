package com.vf.mvc.service;

/**
 * 基础curd服务
 *
 * @author levin
 * @since 1.0.0
 */
public interface CurdService<E,DTO,PO> extends BaseService<E,DTO,PO>,
        CreateService<E,DTO,PO>, RetrieveService<E,DTO,PO>,
        RetrievePageService<E,DTO,PO>, UpdateService<E,DTO,PO>, DeleteService<E,DTO,PO> {
}
