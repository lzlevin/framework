package com.vf.mvc.controller;

import com.vf.mvc.service.*;

/**
 * 增删改查controller
 *
 * @author levin
 * @since 1.0.0
 */
public interface CurdController<VO, DTO, E, PO>
        extends BaseController<VO, DTO, E, PO>, CreateController<VO, DTO, E, PO>,
        UpdateController<VO, DTO, E, PO>, RetrieveController<VO, DTO, E, PO>, DeleteController<VO, DTO, E, PO> {

    /**
     * curd服务
     *
     * @param <S>
     * @return curd服务
     */
    <S extends CurdService<E, DTO, PO>> S getService();

    /**
     * 创建服务
     *
     * @return 创建服务
     */
    @Override
    default <S extends CreateService<E, DTO, PO>> S getCreateService() {
        return getService();
    }

    /**
     * 删除服务
     *
     * @return 删除服务
     */
    @Override
    default <S extends DeleteService<E, DTO, PO>> S getDeleteService() {
        return getService();
    }

    /**
     * 查询服务
     *
     * @return 查询服务
     */
    @Override
    default <S extends RetrieveService<E, DTO, PO>> S getRetrieveService() {
        return getService();
    }

    /**
     * 更新服务
     *
     * @return 更新服务
     */
    @Override
    default <S extends UpdateService<E, DTO, PO>> S getUpdateService() {
        return getService();
    }
}
