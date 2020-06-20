package com.vf.mybatis.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.vf.mybatis.constant.BaseEntityConstant;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 填充UseFlag，默认填充启用<br>
 *
 * @author levin
 * @since 1.0
 */
public class UseFlagEntityHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, BaseEntityConstant.KEY_USE_FLAG, Boolean.class, true);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
