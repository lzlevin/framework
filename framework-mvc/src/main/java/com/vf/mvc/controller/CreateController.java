package com.vf.mvc.controller;

import cn.hutool.core.bean.BeanUtil;
import com.vf.mvc.dto.BaseDTO;
import com.vf.mvc.response.ApiResponse;
import com.vf.mvc.service.CreateService;
import com.vf.mvc.vo.BaseVO;
import com.vf.mybatis.entity.BaseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author levin
 * @since 1.0.0
 */
public interface CreateController<VO extends BaseVO<Long>, DTO extends BaseDTO<Long>, E extends BaseEntity<Long>> extends BaseController<VO, DTO, E> {

    <S extends CreateService<E>> S getCreateService();

    /**
     * 新建
     *
     * @param dto
     * @return
     */
    @RequestMapping("create")
    default ApiResponse create(DTO dto) {
        E entity = createEntity();
        BeanUtil.copyProperties(dto, entity);
        boolean save = getCreateService().save(entity);
        return ApiResponse.success(save);
    }
}
