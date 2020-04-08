package com.vf.mvc.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
public class SimplePage<T> {

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

    /**
     * 从{@link IPage}转换，因{@link IPage}序列化太笨重
     *
     * @param page 原始page对象
     * @param <T>  对象类型
     * @return 转换后的简单对象
     */
    public static <T> SimplePage copy(IPage<T> page) {
        SimplePage<T> pageResponse = new SimplePage<>();
        pageResponse.setTotal(page.getTotal());
        pageResponse.setSize(page.getPages());
        pageResponse.setCurrent(page.getCurrent());
        pageResponse.setRecords(page.getRecords());
        return pageResponse;
    }
}
