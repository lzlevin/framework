package com.vf.task.file;

import com.vf.task.common.AbstractTask;
import com.vf.task.file.strategy.FileFilter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.vfs2.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 抽象文件任务
 *
 * @author levin
 * @date 2020/6/3
 * @since 1.0.0
 */
@Slf4j
@Getter
@Setter
public abstract class AbstractFileTask extends AbstractTask {
    public static FileSystemManager DEFAULT_FILE_SYSTEM_MANAGER;

    static {
        try {
            DEFAULT_FILE_SYSTEM_MANAGER = VFS.getManager();
        } catch (FileSystemException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 原始文件url，支持{@link org.apache.commons.vfs2.VFS}<br>
     * 支持目录和文件
     */
    protected String sourceFileUrl;

    /**
     * 文件过滤器，如果<code>sourceFileUrl</code>为文件，则此属性失效<br>
     * 且此属性按照相对于<code>sourceFileUrl</code>的相对路径传入变量
     */
    protected FileFilter filter;

    @Override
    public void init() {
        try {
            FileObject source = DEFAULT_FILE_SYSTEM_MANAGER.resolveFile(sourceFileUrl);
            if (!source.exists()) {
                throw new FileNotFoundException(source.getPublicURIString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据源文件和过滤器扫描要执行的文件
     *
     * @return 文件集合（全路径）
     */
    private List<FileObject> scanFile() throws IOException {
        List<FileObject> files = new ArrayList<>();
        FileObject source = DEFAULT_FILE_SYSTEM_MANAGER.resolveFile(sourceFileUrl);
        if (!source.exists()) {
            return Collections.emptyList();
        }
        if (source.isFile()) {
            files.add(source);
            return files;
        }
        FileObject[] objects = source.findFiles(new AllFileSelector());
        final FileFilter fileFilter = null == filter ? FileFilter.NO_FILE_FILTER : this.filter;
        for (FileObject object : objects) {
            String relativeName = source.getName().getRelativeName(object.getName());
            String baseName = source.getName().getBaseName();
            if (object.isFile() && fileFilter.accept(relativeName, baseName)) {
                files.add(object);
            }
        }
        return files;
    }

    @Override
    public void execute(Object... args) {
        List<FileObject> files = null;
        try {
            files = scanFile();
            log.info("scan {} files from [{}]", files.size(), sourceFileUrl);
            handleFiles(files);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * 处理文件
     *
     * @param fileObjects
     */
    public abstract void handleFiles(List<FileObject> fileObjects) throws IOException;

    @Override
    public void reconfigure() {

    }
}
