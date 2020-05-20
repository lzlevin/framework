package com.vf.task.groovy;

/**
 * 脚本读取异常
 *
 * @author levin
 * @date 2020/5/14
 * @since 1.0.0
 */
public class GroovyScriptException extends Exception {

    private static final String MSG_NOT_EXIST = "groovy script [%s] not exist";
    private static final String MSG_PARSE_ERROR = "groovy script [%s] load error";
    private static final String MSG_RUN_ERROR = "groovy script [%s] run error";

    /**
     * 脚本异常
     *
     * @param key 脚本名称
     */
    private GroovyScriptException(String key) {
        super(key);
    }

    /**
     * 脚本不存在
     *
     * @param scriptName 脚本名称
     * @param ex         原始异常
     * @return 异常
     */
    public static GroovyScriptException notExist(String scriptName, Throwable ex) {
        return throwNewException(MSG_NOT_EXIST, scriptName, ex);
    }

    /**
     * 脚本格式错误
     *
     * @param scriptName 脚本名称
     * @param ex         原始异常
     * @return 异常
     */
    public static GroovyScriptException loadError(String scriptName, Throwable ex) {
        return throwNewException(MSG_PARSE_ERROR, scriptName, ex);
    }

    /**
     * 脚本运行错误
     *
     * @param scriptName 脚本名称
     * @param ex         原始异常
     * @return 异常
     */
    public static GroovyScriptException runError(String scriptName, Throwable ex) {
        return throwNewException(MSG_RUN_ERROR, scriptName, ex);
    }

    /**
     * 脚本异常
     *
     * @param template   模板
     * @param scriptName 脚本名称
     * @param ex         原始异常
     * @return
     */
    private static GroovyScriptException throwNewException(String template, String scriptName, Throwable ex) {
        if (null == ex) {
            return new GroovyScriptException(String.format(template, scriptName));
        } else {
            return new GroovyScriptException(String.format(template, scriptName), ex);
        }
    }

    /**
     * 脚本异常
     *
     * @param msg 脚本名称
     * @param ex  异常
     */
    public GroovyScriptException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
