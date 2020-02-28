package com.vin.framework.core.dto;

import com.vin.framework.core.common.Idable;
import com.vin.framework.core.common.Pageable;
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
public abstract class BaseDTO<PK> implements Idable<PK>, Pageable, Serializable {
    /**
     *
     */
    private PK id;
    /**
     * 每页显示条数，默认 10
     */
    private Long size = DEFAULT_SIZE;

    /**
     * 当前页
     */
    private Long current = DEFAULT_SIZE;
}
