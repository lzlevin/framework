package com.vf.task.groovy;

/**
 * 脚本读取异常
 *
 * @author levin
 * @date 2020/5/14
 * @since 1.0.0
 */
public class GroovyScriptException extends Exception {

    private static final String MSG = "groovy script [%s] load error";

    /**
     * 脚本异常
     *
     * @param key 脚本名称
     */
    public GroovyScriptException(String key) {
        super(String.format(MSG, key));
    }

    /**
     * 脚本异常
     *
     * @param key 脚本名称
     * @param ex  异常
     */
    public GroovyScriptException(String key, Throwable ex) {
        super(String.format(MSG, key), ex);
    }
}
