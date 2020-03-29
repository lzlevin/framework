package com.vf.mvc.vo;

import com.vf.common.entity.Idable;
import lombok.Data;

/**
 * 简单的key+name形式的VO
 *
 * @author levin
 * @since 1.0.0
 */
@Data
public class NameVO<T> extends BaseVO<T> implements Idable<T> {
    /**
     * 名称
     */
    private String name;
}
