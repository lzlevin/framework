package com.vin.framework.core.vo;

import com.vin.framework.core.common.Idable;
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
