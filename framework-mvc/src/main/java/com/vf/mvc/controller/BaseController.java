package com.vf.mvc.controller;

import com.vf.utils.bean.BeanUtil;
import lombok.SneakyThrows;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * po集合转vo集合
     *
     * @param pos po集合
     * @return vo集合
     */
    default List<VO> toVos(List<PO> pos) {
        if (CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }
        List<VO> collect = pos.stream().map(this::createVO).collect(Collectors.toList());
        return collect;
    }
}
