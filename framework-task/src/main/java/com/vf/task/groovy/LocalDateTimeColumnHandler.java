package com.vf.task.groovy;

import org.apache.commons.dbutils.ColumnHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * <code>LocalDateTime</>转换
 *
 * @author levin
 * @date 2020/5/14
 * @see org.apache.commons.dbutils.ColumnHandler
 * @since 1.0.0
 */
public class LocalDateTimeColumnHandler implements ColumnHandler {

    @Override
    public boolean match(Class<?> propType) {
        return LocalDateTime.class.equals(propType);
    }

    @Override
    public Object apply(ResultSet rs, int columnIndex) throws SQLException {
        if (null != rs.getTimestamp(columnIndex)) {
            return rs.getTimestamp(columnIndex).toLocalDateTime();
        }
        return null;
    }
}
