package com.vf.mvc.service;

import com.vf.common.entity.Idable;
import com.vf.common.entity.UseFlag;
import com.vf.log.annotation.Log;
import com.vf.utils.lang.Assert;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 具备{@code useFlag}属性的服务，提供启用，停用，批量启用，批量停用等功能
 *
 * @author levin
 * @date 2020/5/4
 * @since 1.0.0
 */
public interface UseFlagService<E extends UseFlag & Idable<ID>, ID>
        extends BaseService<E, E, E> {

    /**
     * 根据ID启用
     *
     * @param id id
     */
    @Log(action = "启用")
    default void enable(ID id) {
        update(Arrays.asList(id), UseFlag.ENABLE);
    }

    /**
     * 根据ID禁用
     *
     * @param id id
     */
    @Log(action = "禁用")
    default void disable(ID id) {
        update(Arrays.asList(id), UseFlag.DISABLE);
    }

    /**
     * 更新启用/禁用状态
     *
     * @param ids     批量ID
     * @param useFlag 启用/禁用
     */
    @Log(action = "批量禁启用")
    default void update(Collection<ID> ids, Boolean useFlag) {
        Assert.isEmpty(ids, "ID不能为空");
        ids = ids.stream().filter(Objects::nonNull).collect(Collectors.toList());
        List<E> collect = ids.stream().map(t -> {
            E entity = createEntity();
            entity.setUseFlag(useFlag);
            entity.setId(t);
            return entity;
        }).collect(Collectors.toList());
        getDao().updateBatchById(collect);
    }

    /**
     * 根据ID批量启用
     *
     * @param ids 批量ID
     */
    @Log(action = "批量启用")
    default void enable(Collection<ID> ids) {
        update(ids, UseFlag.ENABLE);
    }

    /**
     * 根据ID批量启用
     *
     * @param ids 批量ID
     */
    @Log(action = "批量禁用")
    default void disable(Collection<ID> ids) {
        update(ids, UseFlag.DISABLE);
    }
}
