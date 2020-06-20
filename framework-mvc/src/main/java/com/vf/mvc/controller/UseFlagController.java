package com.vf.mvc.controller;

import cn.hutool.core.util.StrUtil;
import com.vf.common.entity.Idable;
import com.vf.common.entity.UseFlag;
import com.vf.common.exception.BusinessException;
import com.vf.mvc.response.ApiResponse;
import com.vf.mvc.service.UseFlagService;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 禁启用基础controller，提供单个和批量的禁用/启用功能<br>
 *
 * @param <VO>  VO
 * @param <DTO> DTO
 * @param <E>   E
 * @param <PO>  PO
 * @author first
 * @since 1.0
 */
public interface UseFlagController<VO, DTO, E extends UseFlag & Idable<Long>, PO>
        extends BaseController<VO, DTO, E, PO> {
    /**
     * 默认ID分隔符
     */
    public static final String DEFAULT_ID_SPLIT = ",";

    /**
     * 禁启用服务
     *
     * @param <S>
     * @return 禁启用服务
     */
    <S extends UseFlagService<E, Long>> S getUseFlagService();

    /**
     * 单个启用
     *
     * @param id 启用ID
     * @return 是否成功
     */
    @RequestMapping("enable")
    default ApiResponse enable(Long id) {
        getUseFlagService().enable(id);
        return ApiResponse.success(true);
    }

    /**
     * 单个禁用
     *
     * @param id 禁用ID
     * @return 禁用ID
     */
    @RequestMapping("disable")
    default ApiResponse disable(Long id) {
        getUseFlagService().disable(id);
        return ApiResponse.success(true);
    }

    /**
     * 批量启用
     *
     * @param ids 批量ID
     * @return 是否成功
     */
    @RequestMapping("enableBatch")
    default ApiResponse enable(String ids) {
        List<Long> collect = convertIds(ids);
        getUseFlagService().enable(collect);
        return ApiResponse.success(true);
    }

    /**
     * 转化ID为long格式
     *
     * @param ids ID
     * @return 转化后的ID
     */
    default List<Long> convertIds(String ids) {
        long[] longs = null;
        try {
            longs = StrUtil.splitToLong(ids, DEFAULT_ID_SPLIT);
        } catch (Exception exception) {
            throw new BusinessException("参数格式有误");
        }
        return Arrays.stream(longs).boxed().collect(Collectors.toList());
    }

    /**
     * 批量禁用
     *
     * @param ids 禁用ID
     * @return 禁用ID
     */
    @RequestMapping("disableBatch")
    default ApiResponse disable(String ids) {
        getUseFlagService().disable(convertIds(ids));
        return ApiResponse.success(true);
    }
}
