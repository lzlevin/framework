package com.vf.mvc.controller;

import com.vf.utils.bean.BeanUtil;
import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;

/**
 * 基础controller接口
 *
 * @author levin
 * @since 1.0.0
 */
public interface BaseController<VO, DTO, E, PO> {

    /**
     * 创建VO
     *
     * @return
     */
    @SneakyThrows
    default VO createVO(PO po) {
        return BeanUtil.copyPropertiesClazz(po, getClassVO());
    }

    /**
     * 获取VO的class
     *
     * @return VO的class
     */
    default Class<VO> getClassVO() {
        return (Class<VO>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[3];
    }
}
