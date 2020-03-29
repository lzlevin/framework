package com.vf.mvc.controller;

import cn.hutool.core.bean.BeanUtil;
import com.vf.mvc.dto.BaseDTO;
import com.vf.mvc.response.ApiResponse;
import com.vf.mvc.service.DeleteService;
import com.vf.mvc.vo.BaseVO;
import com.vf.mybatis.entity.BaseEntity;
import com.vf.validate.group.DeleteGroup;
import com.vf.validate.validator.BeanValidator;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author levin
 * @since 1.0.0
 */
public interface DeleteController<VO extends BaseVO<Long>, DTO extends BaseDTO<Long>, E extends BaseEntity<Long>>
        extends BaseController<VO, DTO, E> {

    <S extends DeleteService<E>> S getDeleteService();

    /**
     * 根据主键删除
     *
     * @param dto 主键
     * @return
     */
    @RequestMapping("delete")
    default ApiResponse delete(DTO dto) {
        E entity = createEntity();
        BeanUtil.copyProperties(dto, entity);
        BeanValidator.validate(entity, DeleteGroup.class);
        boolean save = getDeleteService().removeById(entity.getId());
        return ApiResponse.success(save);
    }
}
