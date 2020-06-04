package com.vf.task.file.file;

/**
 * 重命名策略
 *
 * @author levin
 * @date 2020/6/2
 * @since 1.0.0
 */
public interface FileRenameHandler {

    String CURRENT_DIRECTORY = ".";

    FileRenameHandler NO_RENAME_HANDLER = new FileRenameHandler() {
        @Override
        public String rename(String relativeName, String name) {
            return relativeName;
        }
    };

    /**
     * 重命名
     *
     * @param name         文件名
     * @param relativeName 相对路径名称
     * @return 处理完成后的相对路径
     */
    String rename(String relativeName, String name);

    /**
     * 获取路径
     *
     * @param relativeName 相对路径
     * @param name         文件名
     * @return 相对路径
     */
    default String getPath(String relativeName, String name) {
        if (null == relativeName || null == name || name.length() > relativeName.length()) {
            throw new IllegalArgumentException("name length must greater than relativeName");
        }
        return relativeName.substring(relativeName.length() - name.length());
    }
}
