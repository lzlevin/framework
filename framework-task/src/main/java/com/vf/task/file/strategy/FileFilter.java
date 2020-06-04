package com.vf.task.file.strategy;

/**
 * 文件过滤器
 *
 * @author levin
 * @date 2020/6/3
 * @since 1.0.0
 */
public interface FileFilter {

    /**
     * 不过滤文件过滤器
     */
    FileFilter ALL_FILE_FILTER = new FileFilter() {
        @Override
        public boolean accept(String relativeName, String name) {
            return false;
        }
    };

    /**
     * 过滤所有文件过滤器
     */
    FileFilter NO_FILE_FILTER = new FileFilter() {
        @Override
        public boolean accept(String relativeName, String name) {
            return true;
        }
    };

    /**
     * @param relativeName 相对路径
     * @param name         文件名
     * @return 是否过滤
     */
    boolean accept(String relativeName, String name);
}
