package com.vin.framework.admin.service.impl;

import com.vin.framework.mybatis.service.impl.ServiceImpl;
import com.vin.framework.admin.entity.UserEntity;
import com.vin.framework.admin.mapper.UserMapper;
import com.vin.framework.admin.service.UserBusiness;

/**
 * @author levin
 * @since 1.0.0
 */
public class UserBusinessImpl extends ServiceImpl<UserMapper, UserEntity> implements UserBusiness {
}
