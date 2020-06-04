package com.vf.mvc.controller;

import com.vf.mvc.response.ApiResponse;
import com.vf.mvc.service.UpdateService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 更新controller
 *
 * @author levin
 * @since 1.0.0
 */
public interface UpdateController<VO, DTO, E, PO>
        extends BaseController<VO, DTO, E, PO> {

    /**
     * 更新服务
     *
     * @param <S>
     * @return 更新服务
     */
    <S extends UpdateService<E, DTO, PO>> S getUpdateService();

    /**
     * 根据主键更新
     *
     * @param dto 用户数据
     * @return
     */
    @RequestMapping("update")
    default ApiResponse update(DTO dto) {
        boolean save = getUpdateService().updateById(dto);
        return ApiResponse.success(save);
    }
}
