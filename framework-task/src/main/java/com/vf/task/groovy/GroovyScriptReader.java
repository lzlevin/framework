package com.vf.task.groovy;

import java.time.LocalDateTime;

/**
 * Groovy脚本资源连接器
 *
 * @author levin
 * @date 2020/5/13
 * @see groovy.util.ResourceConnector
 * @see org.springframework.core.io.Resource
 * @since 1.0.0
 */
public interface GroovyScriptReader {

    /**
     * 获取脚本的内容
     *
     * @param key 脚本键（唯一确定）
     * @return 脚本内容
     * @throws GroovyScriptException 脚本读取错误
     */
    String getContent(String key) throws GroovyScriptException;

    /**
     * 获取script的最后修改时间
     *
     * @param key script时间
     * @return 最后修改时间
     * @throws GroovyScriptException 脚本读取错误
     */
    LocalDateTime getModifiedTime(String key) throws GroovyScriptException;
}
