package com.vf.log.listener;

import cn.hutool.core.util.StrUtil;
import com.vf.log.event.LogAfterEvent;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link javax.sql.DataSource}的日志监听器
 *
 * @author levin
 * @since 1.0.0
 */
@Slf4j
public class DatasourceLogListener implements LogListener {

    public static final String DEFAULT_PREFIX = "INSERT INTO ";
    public static final String DEFAULT_SUFFIX = " (BATCH,FUNCTION,ACTION,DESCRIPTION,CLAZZ,METHOD,REQUEST_TIME,REQUEST,RESPONSE_TIME,RESPONSE,THROWING,SUCCESS,URL,HOST,USER_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String DEFAULT_TABLE = "SYS_LOG";

    /**
     * 数据源
     */
    private final DataSource dataSource;
    /**
     * 插入sql语句
     */
    private String sql;

    /**
     * 日志监听器
     *
     * @param dataSource 数据源
     * @param table      写入的表
     */
    public DatasourceLogListener(DataSource dataSource, String table) {
        if (null == dataSource) {
            throw new IllegalArgumentException("datasource must be not null");
        }
        this.dataSource = dataSource;
        if (StrUtil.isBlank(table)) {
            this.sql = DEFAULT_PREFIX + DEFAULT_TABLE + DEFAULT_SUFFIX;
        } else {
            this.sql = DEFAULT_PREFIX + table + DEFAULT_SUFFIX;
        }

    }

    /**
     * 日志监听器
     *
     * @param dataSource 数据源
     */
    public DatasourceLogListener(DataSource dataSource) {
        this(dataSource, null);
    }

    /**
     * 执行完毕后的日志记录
     *
     * @param event
     */
    @Override
    public void after(LogAfterEvent event) {
        execute(event);
    }

    /**
     * 数据库保存事件信息
     *
     * @param event 事件
     */
    private void execute(LogAfterEvent event) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            List<Object> params = getParams(event);
            for (int index = 0; index < params.size(); index++) {
                statement.setObject(index + 1, params.get(index));
            }
            statement.executeUpdate();
        } catch (Exception e) {
            log.debug("log write error.", e);
        } finally {
            if (null != connection) {
                try {
                    if (!connection.getAutoCommit()) {
                        connection.commit();
                    }
                } catch (SQLException e) {
                    log.debug("log commit error", e);
                }
                try {
                    connection.close();
                } catch (SQLException ex) {
                    log.debug("log close error", ex);
                }
            }
        }
    }

    /**
     * 将event字段转化为list
     *
     * @param event 事件
     * @return 字段集合
     */
    private List<Object> getParams(LogAfterEvent event) {
        List<Object> params = new ArrayList<>();
        params.add(event.getBatch());
        params.add(event.getFunction());
        params.add(event.getAction());
        params.add(event.getDescription());
        params.add(event.getClazz().getName());
        params.add(event.getMethod().getName());
        params.add(event.getResponseTime());
        params.add(convert(event.getRequest()));
        params.add(event.getResponseTime());
        params.add(convert(event.getResponse()));
        params.add(convert(event.getThrowable()));
        params.add(event.getSuccess());
        params.add(event.getUrl());
        params.add(event.getHost());
        params.add(event.getUserId());
        return params;
    }

    /**
     * 对象转字符
     *
     * @param o
     * @return
     */
    private String convert(Object o) {
        return null == o ? null : o.toString();
    }

    /**
     * 获取顺序，顺序越大越先执行
     *
     * @return 顺序，用于触发次序
     */
    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 是否异步
     *
     * @return 是否异步
     */
    @Override
    public boolean isAsync() {
        return true;
    }
}
