package com.vf.mvc.vo;

import com.vf.common.entity.Idable;
import lombok.Data;

import java.io.Serializable;

/**
 * 默认VO
 *
 * @author levin
 * @since 1.0.0
 */
@Data
public abstract class BaseVO<T> implements Serializable, Idable<T> {

    private T id;

}
