package com.vin.framework.mybatis.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.vin.framework.core.constant.BaseEntityConstant;
import com.vin.framework.core.entity.BaseEntity;
import com.vin.framework.core.user.UserHolder;
import org.apache.ibatis.reflection.MetaObject;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基于{@link MetaObjectHandler}实现{@link BaseEntity}的填充
 *
 * @author levin
 * @since 1.0.0
 */
public class EntityHandler implements MetaObjectHandler {

    /**
     * 插入元对象字段填充，填充创建人、创建时间等
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, BaseEntityConstant.KEY_DELETED, Integer.class, BaseEntityConstant.VALUE_DELETED_FALSE);
        this.strictInsertFill(metaObject, BaseEntityConstant.KEY_CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
        Serializable id = UserHolder.getUserId();
        if (id instanceof String) {
            this.strictInsertFill(metaObject, BaseEntityConstant.KEY_CREATE_BY, String.class, id);
        } else if (id instanceof Long) {
            this.strictInsertFill(metaObject, BaseEntityConstant.KEY_CREATE_BY, Long.class, id);
        }

    }

    /**
     * 更新元对象字段填充，填充修改人、修改时间等
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, BaseEntityConstant.KEY_MODIFY_TIME, LocalDateTime.class, LocalDateTime.now());
        Serializable id = UserHolder.getUserId();
        if (id instanceof String) {
            this.strictUpdateFill(metaObject, BaseEntityConstant.KEY_MODIFY_BY, String.class, id);
        } else if (id instanceof Long) {
            this.strictUpdateFill(metaObject, BaseEntityConstant.KEY_MODIFY_BY, Long.class, id);
        }
    }
}
