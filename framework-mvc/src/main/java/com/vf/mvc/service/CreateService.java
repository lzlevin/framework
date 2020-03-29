package com.vf.mvc.service;

import com.vf.log.annotation.Log;
import com.vf.mybatis.entity.BaseEntity;
import com.vf.validate.group.CreateGroup;
import com.vf.validate.validator.BeanValidator;

import java.util.Collection;

/**
 * 基础新增服务
 *
 * @author levin
 * @since 1.0.0
 */
public interface CreateService<E extends BaseEntity<Long>> extends BaseService<E> {

    /**
     * 新增一条<br>
     * 暂未采用{@link org.springframework.validation.annotation.Validated}注解
     *
     * @param entity 新增的数据
     * @return 是否新增成功
     */
    @Log(action = "新增")
    default boolean save(E entity) {
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
    default int saveInBatch(Collection<E> list) {
        list.forEach(t -> BeanValidator.validate(t, CreateGroup.class));
        return getDao().saveInBatch(list);
    }
}
