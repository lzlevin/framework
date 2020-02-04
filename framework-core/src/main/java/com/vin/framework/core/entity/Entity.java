package com.vin.framework.core.entity;

import java.io.Serializable;


/**
 * 基础实体类
 *
 * @param <T> 一般用于主键
 * @author levin
 * @since 1.0.0
 */
public interface Entity<T extends Serializable> extends Serializable {
}
