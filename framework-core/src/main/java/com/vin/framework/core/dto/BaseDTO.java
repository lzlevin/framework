package com.vin.framework.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 基础DTO，实现ID，
 *
 * @author levin
 * @since 1.0.0
 */
@Getter
@Setter
public abstract class BaseDTO<PK> implements Serializable {
    /**
     *
     */
    private PK id;
    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;

    /**
     * 当前页
     */
    private long current = 1;
}
