package com.vf.mvc.controller;

import cn.hutool.core.bean.BeanUtil;
import com.vf.mvc.dto.BaseDTO;
import com.vf.mvc.response.ApiResponse;
import com.vf.mvc.service.UpdateService;
import com.vf.mvc.vo.BaseVO;
import com.vf.mybatis.entity.BaseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author levin
 * @since 1.0.0
 */
public interface UpdateController<VO extends BaseVO<Long>, DTO extends BaseDTO<Long>, E extends BaseEntity<Long>>
        extends BaseController<VO, DTO, E> {

    <S extends UpdateService<E>> S getUpdateService();

    /**
     * 根据主键更新
     *
     * @param dto 用户数据
     * @return
     */
    @RequestMapping("update")
    default ApiResponse update(DTO dto) {
        E entity = createEntity();
        BeanUtil.copyProperties(dto, entity);
        boolean save = getUpdateService().updateById(entity);
        return ApiResponse.success(save);
    }
}
