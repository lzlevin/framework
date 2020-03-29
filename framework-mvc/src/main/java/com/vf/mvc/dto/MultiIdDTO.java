package com.vf.mvc.dto;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author levin
 * @since 1.0.0
 */
public class MultiIdDTO extends BaseDTO<String> {

    /**
     * 默认分隔符逗号
     */
    public static final String DEFAULT_SPLIT = ",";

    /**
     * 分隔ID
     *
     * @return 分隔好的ID
     */
    public <T> List<T> split(Class<T> clazz) {
        String id = getId();
        if (StrUtil.isBlank(id)) {
            return Collections.emptyList();
        }
        String[] array = id.split(DEFAULT_SPLIT);
        return Arrays.asList(array).stream()
                .map(t -> Convert.convert(clazz, t))
                .collect(Collectors.toList());
    }
}
