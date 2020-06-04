package com.vf.mvc.service;

import com.vf.log.annotation.Log;
import com.vf.utils.lang.Assert;
import com.vf.validate.group.CreateGroup;
import com.vf.validate.validator.BeanValidator;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基础新增服务
 *
 * @author levin
 * @since 1.0.0
 */
public interface CreateService<E, DTO, PO> extends BaseService<E, DTO, PO> {

    /**
     * 新增一条<br>
     * 暂未采用{@link org.springframework.validation.annotation.Validated}注解
     *
     * @param dto 新增的数据
     * @return 是否新增成功
     */
    @Log(action = "新增")
    default boolean save(DTO dto) {
        Assert.isNull(dto, "新增数据不能为空");
        E entity = createEntity(dto);
        BeanValidator.validate(entity, CreateGroup.class);
        return getDao().save(entity);
    }

    /**
     * 批量新增
     *
     * @param list 新增数据list
     * @return 新增数量
     */
    @Log(action = "批量新增")
    default int saveInBatch(Collection<DTO> list) {
        Assert.isEmpty(list, "批量新增数据不能为空");
        List<E> collect = list.stream().map(t -> createEntity(t)).collect(Collectors.toList());
        collect.forEach(t -> BeanValidator.validate(t, CreateGroup.class));
        return getDao().saveInBatch(collect);
    }
}
