package com.vf.mvc.service;

import com.vf.mybatis.service.IService;
import com.vf.utils.bean.BeanUtil;
import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;

/**
 * @author levin
 * @since 1.0.0
 */
public interface BaseService<E, DTO, PO> {

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
     * 获取entity的class
     *
     * @return entity的class
     */
    default Class<E> getClazzEntity() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }


    /**
     * 创建DTO
     *
     * @return
     */
    @SneakyThrows
    default DTO createDTO() {
        Class<DTO> dto = getClassDTO();
        return dto.newInstance();
    }

    /**
     * 获取DTO的class
     *
     * @return DTO的class
     */
    default Class<DTO> getClassDTO() {
        return (Class<DTO>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[1];
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
     * 获取PO的class
     *
     * @return PO的class
     */
    default Class<PO> getClassPO() {
        return (Class<PO>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[2];
    }

}
