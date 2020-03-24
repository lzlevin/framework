package com.vf.core.vo;

import com.vf.core.common.Idable;
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
