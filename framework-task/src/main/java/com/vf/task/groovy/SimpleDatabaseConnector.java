package com.vf.task.groovy;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 默认数据库脚本读取
 *
 * @author levin
 * @date 2020/5/13
 * @since 1.0.0
 */
public class SimpleDatabaseConnector implements GroovyScriptReader {

    private DataSource dataSource;

    public static String TABLE = "GROOVY_SCRIPT";
    public static String SQL_ALL = "SELECT NAME,CONTENT,MODIFIED FROM " + TABLE + " WHERE NAME = ?";
    public static String SQL_MODIFIED_TIME = "SELECT NAME,MODIFIED FROM " + TABLE + " WHERE NAME = ?";

    public SimpleDatabaseConnector(DataSource dataSource) {
        if (null == dataSource) {
            throw new IllegalArgumentException("datasource is null");
        }
        this.dataSource = dataSource;
    }


    @Override
    public String getContent(String key) throws GroovyScriptException {
        ScriptInfo info = getScriptInfo(SQL_ALL, key);
        return info.getContent();
    }

    @Override
    public LocalDateTime getModifiedTime(String key) throws GroovyScriptException {
        ScriptInfo info = getScriptInfo(SQL_MODIFIED_TIME, key);
        return info.getModified();
    }

    /**
     * 获取脚本信息
     *
     * @param sql sql语句
     * @param key 脚本键
     * @return 脚本信息
     */
    private ScriptInfo getScriptInfo(String sql, String key) throws GroovyScriptException {
        List<ScriptInfo> scriptInfos;
        try {
            scriptInfos = new QueryRunner
                    (dataSource).query(sql, new BeanListHandler<>(ScriptInfo.class), key);
        } catch (SQLException e) {
            throw new GroovyScriptException(key, e);
        }
        if (scriptInfos.isEmpty()) {
            throw new GroovyScriptException(key);
        }
        if (scriptInfos.size() > 1) {
            throw new GroovyScriptException(key);
        }
        return scriptInfos.get(0);
    }


    /**
     * 脚本信息Bean
     */
    @Getter
    @Setter
    public static class ScriptInfo {
        /**
         * 脚本名称
         */
        private String name;
        /**
         * 脚本内容
         */
        private String content;
        /**
         * 脚本最后修改时间
         */
        private LocalDateTime modified;
    }

}
