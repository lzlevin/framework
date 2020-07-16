package com.vf.mvc.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.vf.log.annotation.Log;
import com.vf.mybatis.service.IService;
import com.vf.utils.bean.BeanUtil;
import com.vf.utils.lang.Assert;
import org.springframework.core.ResolvableType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author levin
 * @since 1.0.0
 */
public interface BaseService<E, DTO, PO> {

    /**
     * 返回的最大数据数量，在查询中进行相对判断，反正查询过多数据
     */
    int MAX_COUNT = 1000;

    /**
     * dao
     *
     * @param <D>
     * @return E的dao
     */
    <D extends IService<E>> D getDao();

    /**
     * 创建entity
     *
     * @param dto DTO转化为entity
     * @return entity
     */
    default E createEntity(DTO dto) {
        if (null == dto) {
            return null;
        } else if (dto.getClass().equals(getClazzEntity())) {
            return (E) dto;
        } else {
            return BeanUtil.copyPropertiesClazz(dto, getClazzEntity());
        }
    }

    /**
     * 创建实体
     *
     * @return 实体
     */
    default E createEntity() {
        try {
            Class<E> clazzEntity = getClazzEntity();
            return clazzEntity.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 创建实体集合
     *
     * @param list dto集合
     * @return 实体集合
     */
    default List<E> createEntityList(List<DTO> list) {
        if (null == list || list.isEmpty()) {
            return Collections.emptyList();
        }
        return list.stream().map(this::createEntity).collect(Collectors.toList());
    }

    /**
     * 获取entity的class
     *
     * @return entity的class
     */
    default Class<E> getClazzEntity() {
        ResolvableType as = ResolvableType.forClass(getClass()).as(BaseService.class);
        ResolvableType generic = as.getGeneric(0);
        return (Class<E>) generic.toClass();
    }

    /**
     * 创建PO
     *
     * @return
     */
    default PO createPO(E entity) {
        if (null == entity) {
            return null;
        } else if (entity.getClass().equals(getClassPO())) {
            return (PO) entity;
        } else {
            return BeanUtil.copyPropertiesClazz(entity, getClassPO());
        }
    }

    /**
     * 创建po集合
     *
     * @param entity 实体集合
     * @return po集合
     */
    default List<PO> createPOList(Collection<E> entity) {
        if (null == entity || entity.isEmpty()) {
            return Collections.emptyList();
        }
        return entity.stream().map(this::createPO).collect(Collectors.toList());
    }

    /**
     * 获取PO的class
     *
     * @return PO的class
     */
    default Class<PO> getClassPO() {
        ResolvableType as = ResolvableType.forClass(getClass()).as(BaseService.class);
        ResolvableType generic = as.getGeneric(2);
        return (Class<PO>) generic.toClass();
    }

    /**
     * 获取DTO的class
     *
     * @return DTO class
     */
    default Class<DTO> getClassDTO() {
        ResolvableType as = ResolvableType.forClass(getClass()).as(BaseService.class);
        return (Class<DTO>) as.getGeneric(1).toClass();
    }

    /**
     * 根据条件查询
     *
     * @param wrapper 查询条件
     * @return 查询结果
     */
    @Log(action = "根据条件查询最大值")
    default Object max(Wrapper<E> wrapper) {
        Assert.isNull(wrapper, "查询条件不能为空");
        return getDao().max(wrapper);
    }

    /**
     * 根据条件查询
     *
     * @param wrapper 查询条件
     * @return 查询结果
     */
    @Log(action = "根据条件查询最小值")
    default Object min(Wrapper<E> wrapper) {
        Assert.isNull(wrapper, "查询条件不能为空");
        return getDao().min(wrapper);
    }
}
