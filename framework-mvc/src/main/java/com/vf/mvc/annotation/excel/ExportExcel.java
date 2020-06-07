package com.vf.mvc.annotation.excel;

/**
 * 导出excel注解，用于将实体对象转化为excel导出
 *
 * @author levin
 * @date 2020/6/7
 * @since 1.0.0
 */
public @interface ExportExcel {

    /**
     * 写入的sheet索引
     *
     * @return 写入的sheet索引
     */
    int sheet() default 0;

    /**
     * 开始写入的行索引
     *
     * @return 开始写入的行索引
     */
    int rowStart() default 0;

    /**
     * 写入的列索引，如果中间需要跳过则为-1
     *
     * @return 写入的列索引，如果中间需要跳过则为-1
     */
    int[] columns();

    /**
     * 写入的列头名称
     *
     * @return 写入的列头名称，默认从rowStart前一行写入，最好设置好相应的模板并通过模板数据写入的方式导出
     */
    String[] columnName() default {};
}
