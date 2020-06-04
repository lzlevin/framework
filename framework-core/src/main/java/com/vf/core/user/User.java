package com.vf.core.user;

import com.vf.common.entity.Idable;

import java.io.Serializable;

/**
 * 通用用户类
 *
 * @author levin
 * @since 1.0.0
 */
public interface User<PK extends Serializable> extends Idable<PK> {
}
