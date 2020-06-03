package com.vf.task.file.db;

import com.vf.task.file.AbstractFileTask;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.vfs2.FileObject;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * 文件到数据库
 *
 * @author levin
 * @since 1.0.0
 */
@Slf4j
@Getter
@Setter
public class File2DbTask extends AbstractFileTask {

    /**
     * 数据源
     */
    private DataSource dataSource;
    /**
     * 要插入数据的表
     */
    private String table;

    @Override
    public void handleFiles(List<FileObject> fileObjects) throws IOException {

    }
}
