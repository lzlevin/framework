package com.vf.mvc.controller;

import com.vf.utils.bean.BeanUtil;
import org.springframework.core.ResolvableType;
import org.springframework.util.CollectionUtils;

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
    default VO createVO(PO po) {
        if (null == po) {
            return null;
        } else if (po.getClass() == getClassVO()) {
            return (VO) po;
        } else {
            return BeanUtil.copyPropertiesClazz(po, getClassVO());
        }
    }

    /**
     * 获取VO的class
     *
     * @return VO的class
     */
    default Class<VO> getClassVO() {
        ResolvableType as = ResolvableType.forClass(getClass()).as(BaseController.class);
        ResolvableType generic = as.getGeneric(0);
        return (Class<VO>) generic.toClass();
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
        return pos.stream().map(this::createVO).collect(Collectors.toList());
    }
}
