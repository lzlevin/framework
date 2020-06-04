package com.vf.mvc.service;

import com.vf.log.annotation.Log;
import com.vf.utils.lang.Assert;
import com.vf.validate.group.UpdateGroup;
import com.vf.validate.validator.BeanValidator;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基础更新服务
 *
 * @author levin
 * @since 1.0.0
 */
public interface UpdateService<E, DTO, PO> extends BaseService<E, DTO, PO> {
    /**
     * 根据ID更新
     *
     * @param dto 更新的数据
     * @return 是否更新成功
     */
    @Log(action = "更新")
    default boolean updateById(DTO dto) {
        Assert.isNull(dto, "更新数据不能为空");
        E entity = createEntity(dto);
        BeanValidator.validate(entity, UpdateGroup.class);
        return getDao().updateById(entity);
    }

    /**
     * 批量更新
     *
     * @param list 更新数据list
     * @return 更新成功
     */
    @Log(action = "批量更新")
    default boolean updateBatchById(Collection<DTO> list) {
        Assert.isEmpty(list, "批量更新数据不能为空");
        List<E> collect = list.stream().map(t -> createEntity(t)).collect(Collectors.toList());
        collect.forEach(t -> BeanValidator.validate(t, UpdateGroup.class));
        return getDao().updateBatchById(collect);
    }
}
