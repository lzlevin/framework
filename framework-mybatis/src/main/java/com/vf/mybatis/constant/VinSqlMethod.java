package com.vf.mybatis.constant;

/**
 * <br>
 *
 * @author levin
 * @since 1.0
 */
public enum VinSqlMethod {
    MAX("max", "查询满足条件总记录数", "<script>\nSELECT MAX(%s) FROM %s %s %s\n</script>"),
    MIN("min", "查询满足条件总记录数", "<script>\nSELECT MAX(%s) FROM %s %s %s\n</script>");

    private final String method;
    private final String desc;
    private final String sql;

    VinSqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return method;
    }

    public String getDesc() {
        return desc;
    }

    public String getSql() {
        return sql;
    }
}
