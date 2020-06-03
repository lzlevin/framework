package com.vf.task.file.file;

import com.vf.task.common.AbstractTask;
import com.vf.task.file.strategy.FileFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 文件到文件任务，参考{@link org.apache.commons.vfs2.tasks.AbstractSyncTask}
 *
 * @author levin
 * @since 1.0.0
 */
@Slf4j
public class File2FileTask extends AbstractTask {

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
    private String sourceFileUrl;
    /**
     * 目标文件url<br>
     * 支持目录和文件，如果{@code sourceFileUrl}为文件路径，则此属性也必须为文件路径<br>
     */
    private String destFileUrl;

    /**
     * 是否覆盖已存在的文件，如果不覆盖且文件存在，根据skipOnError属性执行相关策略
     */
    private boolean overwrite;

    /**
     * 是否跳过同步失败的文件
     */
    private boolean skipOnError;
    /**
     * 重命名策略
     */
    private FileRenameHandler fileRenameHandler;
    /**
     * 文件过滤器，如果<code>sourceFileUrl</code>为文件，则此属性失效<br>
     * 且此属性按照相对于<code>sourceFileUrl</code>的相对路径传入变量
     */
    private FileFilter filter;
    /**
     * 文件处理器
     */
    private FileHandler handler;

    @Override
    public void init() {
        try {
            FileObject source = DEFAULT_FILE_SYSTEM_MANAGER.resolveFile(sourceFileUrl);
            FileObject dest = DEFAULT_FILE_SYSTEM_MANAGER.resolveFile(destFileUrl);
            if (source.isFolder() && dest.isFile()) {
                throw new RuntimeException("if the source is a directory, the dest must also be a directory");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 操作扫描到的文件
     *
     * @param files 源文件
     */
    private void handleFiles(List<FileObject> files) throws IOException {
        List<FileObject> destList = new ArrayList<>(files.size());
        FileObject source = DEFAULT_FILE_SYSTEM_MANAGER.resolveFile(sourceFileUrl);
        FileObject dest = DEFAULT_FILE_SYSTEM_MANAGER.resolveFile(destFileUrl);
        for (FileObject item : files) {
            String relativeName = source.getName().getRelativeName(item.getName());
            String baseName = source.getName().getBaseName();
            final FileRenameHandler renameHandler = this.fileRenameHandler == null ? FileRenameHandler.NO_RENAME_HANDLER : this.fileRenameHandler;
            if (!dest.getName().isFile()) {
                destList.add(dest.resolveFile(renameHandler.rename(relativeName, baseName)));
            } else {
                destList.add(dest);
            }
        }
        AtomicInteger sum = new AtomicInteger(0);
        try {
            for (int i = 0; i < files.size(); i++) {
                //TODO ThreadPool
                boolean result = handleFile(files.get(i), destList.get(i));
                if (result) {
                    sum.getAndIncrement();
                }
            }
        } finally {
            log.info("copy {} files from [{}] to [{}]", sum.get(), source.getPublicURIString(), dest.getPublicURIString());
        }
    }

    /**
     * 操作扫描到的文件
     *
     * @param source 源文件
     * @param dest   目标文件
     */
    private boolean handleFile(FileObject source, FileObject dest) throws IOException {
        if (dest.exists() && !overwrite) {
            log.info("skip file from [{}] to [{}]", source.getPublicURIString(), dest.getPublicURIString());
            return false;
        }
        final FileHandler fileHandler = null == handler ? FileHandler.NO_HANDLER : handler;
        try (InputStream inputStream = fileHandler.hand(source.getContent().getInputStream());
             OutputStream outputStream = dest.getContent().getOutputStream()) {
            IOUtils.copyLarge(inputStream, outputStream);
            log.debug("copy completed from [{}] to [{}]", source.getPublicURIString(), dest.getPublicURIString());
            return true;
        } catch (IOException ex) {
            if (skipOnError) {
                log.warn("copy error from [{}] to [{}]", source.getPublicURIString(), dest.getPublicURIString());
                return false;
            } else {
                throw ex;
            }
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
            ex.printStackTrace();
        }

    }

    @Override
    public void reconfigure() {

    }
}
