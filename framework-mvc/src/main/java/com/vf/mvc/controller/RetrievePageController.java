package com.vf.mvc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vf.mvc.response.ApiResponse;
import com.vf.mvc.response.SimplePage;
import com.vf.mvc.service.RetrievePageService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 分页查询controller
 *
 * @author levin
 * @since 1.0.0
 */
public interface RetrievePageController<VO, DTO, E, PO>
        extends BaseController<VO, DTO, E, PO> {

    /**
     * 分页查询服务
     *
     * @param <S>
     * @return 分页查询服务
     */
    <S extends RetrievePageService<E, DTO, PO>> S getRetrievePageService();


    /**
     * 分页查询
     *
     * @param current
     * @param size
     * @param dto
     * @return
     */
    @RequestMapping("page/{current}/{size}")
    default ApiResponse page(@PathVariable Long current, @PathVariable Long size, DTO dto) {
        Page<E> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        IPage<PO> result = getRetrievePageService().page(page, dto);
        IPage<VO> convert = result.convert(t -> createVO(t));
        return ApiResponse.success(SimplePage.copy(convert));
    }
}
