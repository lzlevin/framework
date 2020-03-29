package com.vf.mvc.controller;

import com.vf.mvc.dto.BaseDTO;
import com.vf.mvc.vo.BaseVO;
import com.vf.mybatis.entity.BaseEntity;
import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;

/**
 * @author levin
 * @since 1.0.0
 */
public interface BaseController<VO extends BaseVO<Long>, DTO extends BaseDTO<Long>, E extends BaseEntity<Long>> {

    /**
     * 创建VO
     *
     * @return
     */
    @SneakyThrows
    default VO createVO() {
        Class<VO> vo = (Class<VO>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        return vo.newInstance();
    }

    /**
     * 创建DTO
     *
     * @return
     */
    @SneakyThrows
    default DTO createDTO() {
        Class<DTO> dto = (Class<DTO>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[1];
        return dto.newInstance();
    }

    /**
     * 创建entity
     *
     * @return
     */
    @SneakyThrows
    default E createEntity() {
        Class<E> e = (Class<E>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[2];
        return e.newInstance();
    }
}
