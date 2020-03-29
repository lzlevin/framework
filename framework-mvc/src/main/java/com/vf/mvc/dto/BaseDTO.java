package com.vf.mvc.dto;

import com.vf.common.entity.Idable;
import com.vf.common.entity.Pageable;
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
    private Long size = Pageable.DEFAULT_SIZE;

    /**
     * 当前页
     */
    private Long current = Pageable.DEFAULT_SIZE;
}
