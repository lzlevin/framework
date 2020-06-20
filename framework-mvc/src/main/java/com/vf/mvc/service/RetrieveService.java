package com.vf.mvc.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vf.log.annotation.Log;
import com.vf.utils.lang.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基础查询服务
 *
 * @author levin
 * @since 1.0.0
 */
public interface RetrieveService<E, DTO, PO> extends BaseService<E, DTO, PO> {
    /**
     * 根据ID查询
     *
     * @param id 数据ID
     * @return 查询到的数据
     */
    @Log(action = "根据ID查询")
    default PO getById(Serializable id) {
        Assert.isNull(id, "ID不能为空");
        E entity = getDao().getById(id);
        return createPO(entity);
    }

    /**
     * 根据条件查询
     *
     * @param dto 查询条件
     * @return 查询结果
     */
    @Log(action = "根据条件查询")
    default List<PO> list(DTO dto) {
        Assert.isNull(dto, "查询条件不能为空");
        List<E> list = getDao().list(WrapperUtils.wrapper(Wrappers.lambdaQuery(createEntity(dto))));
        return list.stream().map(t -> createPO(t)).collect(Collectors.toList());
    }

    /**
     * 根据条件查询
     *
     * @param wrapper 查询条件
     * @return 查询结果
     */
    @Log(action = "根据条件查询")
    default List<PO> list(Wrapper<E> wrapper) {
        Assert.isNull(wrapper, "查询条件不能为空");
        List<E> list = getDao().list(wrapper);
        return list.stream().map(t -> createPO(t)).collect(Collectors.toList());
    }
}
