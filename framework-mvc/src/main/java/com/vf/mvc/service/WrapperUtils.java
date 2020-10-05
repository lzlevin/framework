package com.vf.mvc.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.vf.common.entity.Sequence;
import com.vf.common.entity.UseFlag;

/**
 * 对{@link com.baomidou.mybatisplus.core.conditions.Wrapper}进行适配性包装<br>
 *
 * @author levin
 * @since 1.0
 */
public final class WrapperUtils {
    /**
     * 对实现了{@link com.vf.common.entity.UseFlag}的查询进行包装
     *
     * @param wrapper 查询wrapper
     * @param <E>     实体类型
     * @return wrapper
     */
    public static <E> Wrapper<E> wrapperUseFlag(Wrapper<E> wrapper) {
        if (wrapper != null || wrapper.getEntity() != null) {
            if (wrapper instanceof LambdaQueryWrapper) {
                LambdaQueryWrapper<E> lambdaQueryWrapper = (LambdaQueryWrapper) wrapper;
                if (lambdaQueryWrapper.getEntity() instanceof UseFlag) {
                    lambdaQueryWrapper.eq(t -> ((UseFlag) t).getUseFlag(), UseFlag.ENABLE);
                }
            }
        }
        return wrapper;
    }

    /**
     * 对实现了{@link com.vf.common.entity.Sequence}的查询进行包装
     *
     * @param wrapper 查询wrapper
     * @param <E>     实体类型
     * @return wrapper
     */
    public static <E> Wrapper<E> wrapperSequence(Wrapper<E> wrapper) {
        if (wrapper != null || wrapper.getEntity() != null) {
            if (wrapper instanceof LambdaQueryWrapper) {
                LambdaQueryWrapper<E> lambdaQueryWrapper = (LambdaQueryWrapper) wrapper;
                if (lambdaQueryWrapper.getEntity() instanceof Sequence) {
                    lambdaQueryWrapper.orderByAsc(t -> ((Sequence) t).getSeq());
                }
            }
        }
        return wrapper;
    }

    /**
     * 对wrapper进行默认处理
     *
     * @param wrapper wrapper
     * @param <E>     实体类型
     * @return wrapper
     */
    public static <E> Wrapper<E> wrapper(Wrapper<E> wrapper) {
        return wrapper;
    }
}
