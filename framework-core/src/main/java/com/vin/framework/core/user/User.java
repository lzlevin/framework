package com.vin.framework.core.user;

import com.vin.framework.core.entity.BaseEntity;

import java.io.Serializable;

/**
 * 通用用户类
 *
 * @author levin
 * @since 1.0.0
 */
public interface User<PK extends Serializable> extends BaseEntity<PK> {
}
