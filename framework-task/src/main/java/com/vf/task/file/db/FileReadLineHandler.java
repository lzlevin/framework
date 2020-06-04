package com.vf.task.file.db;

import com.vf.utils.lang.Assert;
import org.apache.commons.vfs2.FileObject;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author levin
 * @date 2020/6/4
 * @since 1.0.0
 */
public abstract class FileReadLineHandler implements Iterator<Map<String, Object>> {
    /**
     * 文件
     */
    private FileObject fileObject;
    /**
     * 表格列名
     */
    private List<String> tableColumns;

    /**
     * 默认构造器
     *
     * @param fileObject   文件名
     * @param tableColumns 表格的列名
     */
    public FileReadLineHandler(FileObject fileObject, List<String> tableColumns) {
        Assert.isNull(fileObject, "the fileObject argument must be not null");
        Assert.isNull(tableColumns, "the tableColumns argument must be not null");
        this.fileObject = fileObject;
    }

}
