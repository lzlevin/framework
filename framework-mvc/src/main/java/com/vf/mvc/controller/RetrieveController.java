package com.vf.mvc.controller;

import com.vf.mvc.response.ApiResponse;
import com.vf.mvc.service.RetrieveService;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 查询controller
 *
 * @author levin
 * @since 1.0.0
 */
public interface RetrieveController<VO, DTO, E, PO>
        extends BaseController<VO, DTO, E, PO> {

    /**
     * 查询服务
     *
     * @param <S>
     * @return 查询服务
     */
    <S extends RetrieveService<E, DTO, PO>> S getRetrieveService();

    /**
     * 根据条件查询
     *
     * @param dto 条件实体
     * @return api响应
     */
    @RequestMapping("list")
    default ApiResponse list(DTO dto) {
        List<PO> list = getRetrieveService().list(dto);
        List<VO> collect = list.stream().map(t -> createVO(t)).collect(Collectors.toList());
        return ApiResponse.success(collect);
    }

    /**
     * 根据主键获取
     *
     * @param id 主键实体类
     * @return
     */
    @RequestMapping("get")
    default ApiResponse get(Serializable id) {
        PO po = getRetrieveService().getById(id);
        return ApiResponse.success(createVO(po));
    }
}
