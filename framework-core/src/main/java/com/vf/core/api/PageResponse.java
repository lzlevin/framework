package com.vf.core.api;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * 分页数据
 *
 * @author levin
 * @since 1.0.0
 */
@Data
public class PageResponse<T> {

    /**
     * 总数
     */
    private long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;

    /**
     * 当前页
     */
    private long current = 1;
    /**
     *
     */
    private List<T> records = Collections.emptyList();

}
