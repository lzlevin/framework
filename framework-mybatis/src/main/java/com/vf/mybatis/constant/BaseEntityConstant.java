package com.vf.mybatis.constant;

/**
 * 实体字段常量
 * @author levin
 * @since 1.0.0
 */
public final class BaseEntityConstant {
    /**
     * id的key键（java字段/数据库字段）
     */
    public static final String KEY_ID = "id";
    /**
     * 逻辑删除的key键（java字段/数据库字段）
     */
    public static final String KEY_DELETED = "deleted";
    /**
     * 创建人的key键（java字段/数据库字段）
     */
    public static final String KEY_CREATE_BY = "createBy";
    /**
     * 创建时间的key键（java字段/数据库字段）
     */
    public static final String KEY_CREATE_TIME = "createTime";
    /**
     * 修改人的key键（java字段/数据库字段）
     */
    public static final String KEY_MODIFY_BY = "modifyBy";
    /**
     * 修改时间的key键（java字段/数据库字段）
     */
    public static final String KEY_MODIFY_TIME = "modifyTime";
    /**
     * 企业编码的key键（java字段/数据库字段）
     */
    public static final String KEY_CORP_CODE = "corpCode";
    /**
     * 版本号
     */
    public static final String KEY_VERSION = "version";
    /**
     * 序号
     */
    public static final String KEY_SEQ = "seq";
    /**
     * 逻辑删除标识
     */
    public static final Integer VALUE_DELETED_TRUE = 1;
    /**
     * 逻辑未删除标识
     */
    public static final Integer VALUE_DELETED_FALSE = 0;
}
