package com.vf.mvc.controller;

import com.vf.mvc.dto.MultiIdDTO;
import com.vf.mvc.response.ApiResponse;
import com.vf.mvc.service.DeleteService;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 删除controller
 *
 * @author levin
 * @since 1.0.0
 */
public interface DeleteController<VO, DTO, E, PO>
        extends BaseController<VO, DTO, E, PO> {

    /**
     * 删除服务
     *
     * @param <S>
     * @return 删除服务
     */
    <S extends DeleteService<E, DTO, PO>> S getDeleteService();

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return
     */
    @RequestMapping("delete")
    default ApiResponse delete(Serializable id) {
        boolean save = getDeleteService().removeById(id);
        return ApiResponse.success(save);
    }

    /**
     * 根据ID批量删除
     *
     * @param dto 批量ID
     * @return 删除成功
     */
    @RequestMapping("deleteBatch")
    default ApiResponse deleteBatch(MultiIdDTO dto) {
        //TODO 使用ResolveType解决泛型问题
        List<Serializable> split = dto.split(String.class).stream().map(t -> (Serializable) t).collect(Collectors.toList());
        getDeleteService().removeByIds(split);
        return ApiResponse.success(dto.getId());
    }
}
