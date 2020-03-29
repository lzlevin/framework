package com.vf.mvc.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vf.mvc.dto.BaseDTO;
import com.vf.mvc.response.ApiResponse;
import com.vf.mvc.service.RetrieveService;
import com.vf.mvc.vo.BaseVO;
import com.vf.mybatis.entity.BaseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author levin
 * @since 1.0.0
 */
public interface RetrieveController<VO extends BaseVO<Long>, DTO extends BaseDTO<Long>, E extends BaseEntity<Long>>
        extends BaseController<VO, DTO, E> {

    <S extends RetrieveService<E>> S getRetrieveService();

    /**
     * 根据条件查询
     *
     * @param dto 条件实体
     * @return api响应
     */
    @RequestMapping("list")
    default ApiResponse list(DTO dto) {
        E entity = createEntity();
        BeanUtil.copyProperties(dto, entity);
        List<E> list = getRetrieveService().list(Wrappers.query(entity));
        List<VO> collect = list.stream().map(t -> {
            VO po = createVO();
            BeanUtil.copyProperties(t, po);
            return po;
        }).collect(Collectors.toList());
        return ApiResponse.success(collect);
    }

    /**
     * 根据主键获取
     *
     * @param dto 主键实体类
     * @return
     */
    @RequestMapping("get")
    default ApiResponse get(DTO dto) {
        VO po = createVO();
        E user = getRetrieveService().getById(dto.getId());
        BeanUtil.copyProperties(user, po);
        return ApiResponse.success(po);
    }
}
