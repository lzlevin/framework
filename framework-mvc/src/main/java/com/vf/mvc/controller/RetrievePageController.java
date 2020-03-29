package com.vf.mvc.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vf.mvc.dto.BaseDTO;
import com.vf.mvc.response.ApiResponse;
import com.vf.mvc.service.RetrievePageService;
import com.vf.mvc.vo.BaseVO;
import com.vf.mybatis.entity.BaseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author levin
 * @since 1.0.0
 */
public interface RetrievePageController<VO extends BaseVO<Long>, DTO extends BaseDTO<Long>, E extends BaseEntity<Long>>
        extends BaseController<VO, DTO, E> {

    <S extends RetrievePageService<E>> S getRetrievePageService();

    /**
     * 分页查询
     *
     * @param dto
     * @return
     */
    @RequestMapping("page")
    default ApiResponse page(DTO dto) {
        long start = System.currentTimeMillis();
        Page<E> page = new Page<>();
        page.setCurrent(dto.getCurrent());
        page.setSize(dto.getSize());
        E condition = createEntity();
        BeanUtil.copyProperties(dto, condition);
        Page<E> result = getRetrievePageService().page(page, Wrappers.query(condition));
        Page<VO> response = new Page<>();
        BeanUtil.copyProperties(result, response, CopyOptions.create().setIgnoreProperties("records", "optimizeCountSql"));
        List<VO> collect = result.getRecords().stream().map(t -> {
            VO po = createVO();
            BeanUtil.copyProperties(t, po);
            return po;
        }).collect(Collectors.toList());
        response.setRecords(collect);
        return ApiResponse.success(response);
    }
}
