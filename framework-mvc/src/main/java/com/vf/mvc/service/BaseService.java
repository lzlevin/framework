package com.vf.mvc.service;

import com.vf.common.exception.BusinessException;
import com.vf.mybatis.service.IService;
import com.vf.utils.bean.BeanUtil;
import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;
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
    @SneakyThrows
    default E createEntity(DTO dto) {
        return BeanUtil.copyPropertiesClazz(dto, getClazzEntity());
    }

    /**
     * 创建实体
     *
     * @return 实体
     */
    default E createEntity() {
        Class<E> clazzEntity = getClazzEntity();
        try {
            return clazzEntity.newInstance();
        } catch (Exception ex) {
            throw new BusinessException("创建实体错误");
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
        return (Class<E>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }

    /**
     * 创建PO
     *
     * @return
     */
    @SneakyThrows
    default PO createPO(E entity) {
        return BeanUtil.copyPropertiesClazz(entity, getClassPO());
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
        return (Class<PO>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[2];
    }

}
