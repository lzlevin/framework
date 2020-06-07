package com.vf.mvc.annotation.excel;

/**
 * excel导入功能
 *
 * @author levin
 * @date 2020/6/7
 * @since 1.0.0
 */
public @interface ImportExcel {
    /**
     * 导入的sheet
     *
     * @return 导入的sheet
     */
    int sheet() default 0;

    /**
     * 导入的行开始索引
     *
     * @return 导入的行开始索引
     */
    int rowStart() default 0;

    /**
     * 导入的列索引
     *
     * @return 导入的列索引
     */
    int[] columns();

    /**
     * 写入的属性名称，如果为bean则查找相应属性，如果为map则为相应的key键
     *
     * @return 写入的属性名称
     */
    String[] propertyName() default {};
}
