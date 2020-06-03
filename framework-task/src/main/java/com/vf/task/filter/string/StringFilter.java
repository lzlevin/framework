package com.vf.task.filter.string;

import com.vf.task.filter.Filter;

/**
 * @author levin
 * @date 2020/5/25
 * @since 1.0.0
 */
public class StringFilter implements Filter<String> {

    @Override
    public boolean accept(String target) {
        return false;
    }
}
