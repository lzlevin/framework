package com.vf.mybatis.constant;

/**
 * 常量
 *
 * @author levin
 * @since 1.0.0
 */
public interface MethodConstant {
    String KEY_INSERT_IN_BATCH = "insertInBatch";
    String KEY_FOREACH_ITEM = "item";
    String KEY_FOREACH_PREFIX = "item.";
    String KEY_FOREACH_COLLECTION = "list";
    String LOGIC_DELETE_BY_ID = "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>";
}
