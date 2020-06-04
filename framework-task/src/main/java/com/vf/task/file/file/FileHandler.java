package com.vf.task.file.file;

import java.io.InputStream;

/**
 * 文件处理器
 *
 * @author levin
 * @date 2020/6/3
 * @since 1.0.0
 */
public interface FileHandler {

    FileHandler NO_HANDLER = new FileHandler() {
        @Override
        public InputStream hand(InputStream inputStream) {
            return inputStream;
        }
    };

    /**
     * 文件处理
     *
     * @param inputStream 输入流
     * @return 处理完的输入流
     */
    InputStream hand(InputStream inputStream);
}
