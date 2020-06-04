package com.vf.mvc.controller;

import com.vf.mvc.response.ApiResponse;
import com.vf.mvc.service.CreateService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 创建controller
 *
 * @author levin
 * @since 1.0.0
 */
public interface CreateController<VO, DTO, E, PO> extends BaseController<VO, DTO, E, PO> {

    /**
     * 创建服务
     *
     * @param <S>
     * @return 创建服务
     */
    <S extends CreateService<E, DTO, PO>> S getCreateService();

    /**
     * 新建
     *
     * @param dto
     * @return
     */
    @RequestMapping("create")
    default ApiResponse create(DTO dto) {
        boolean save = getCreateService().save(dto);
        return ApiResponse.success(save);
    }
}
