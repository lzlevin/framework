package com.vf.mvc.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vf.log.annotation.Log;
import com.vf.utils.lang.Assert;

/**
 * 基础分页查询服务
 *
 * @author levin
 * @since 1.0.0
 */
public interface RetrievePageService<E, DTO, PO> extends BaseService<E, DTO, PO> {

    /**
     * 根据条件分页查询
     *
     * @param <P>  分页实体
     * @param page 分页参数
     * @param dto  查询条件
     * @return 分页查询结果
     */
    @Log(action = "根据条件分页查询")
    default <P extends IPage<E>> IPage<PO> page(P page, DTO dto) {
        Assert.isNull(page, "分页信息不能为空");
        Assert.isNull(dto, "查询条件不能为空");
        P result = getDao().page(page, WrapperUtils.wrapper(Wrappers.lambdaQuery(createEntity(dto))));
        IPage<PO> convert = result.convert(t -> createPO(t));
        return convert;
    }

    /**
     * 根据条件分页查询
     *
     * @param page    分页参数
     * @param wrapper 查询条件
     * @param <P>     分页实体
     * @return 分页查询结果
     */
    @Log(action = "根据条件分页查询")
    default <P extends IPage<E>> IPage<PO> page(P page, Wrapper<E> wrapper) {
        Assert.isNull(page, "分页信息不能为空");
        Assert.isNull(wrapper, "查询条件不能为空");
        P result = getDao().page(page, wrapper);
        IPage<PO> convert = result.convert(t -> createPO(t));
        return convert;
    }
}
