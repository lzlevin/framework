package com.vf.mvc.controller;

import com.vf.mvc.dto.BaseDTO;
import com.vf.mvc.service.*;
import com.vf.mvc.vo.BaseVO;
import com.vf.mybatis.entity.BaseEntity;

/**
 * @author levin
 * @since 1.0.0
 */
public interface CurdController<VO extends BaseVO<Long>, DTO extends BaseDTO<Long>, E extends BaseEntity<Long>>
        extends BaseController<VO, DTO, E>, CreateController<VO, DTO, E>,
        UpdateController<VO, DTO, E>, RetrieveController<VO, DTO, E>, DeleteController<VO, DTO, E> {

    <S extends CurdService<E>> S getService();

    @Override
    default <S extends CreateService<E>> S getCreateService() {
        return getService();
    }

    @Override
    default <S extends DeleteService<E>> S getDeleteService() {
        return getService();
    }

    @Override
    default <S extends RetrieveService<E>> S getRetrieveService() {
        return getService();
    }

    @Override
    default <S extends UpdateService<E>> S getUpdateService() {
        return getService();
    }
}
