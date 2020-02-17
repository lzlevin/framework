package com.vin.framwork.admin.service.impl;

import com.vin.framework.mybatis.service.impl.ServiceImpl;
import com.vin.framwork.admin.entity.UserEntity;
import com.vin.framwork.admin.mapper.UserMapper;
import com.vin.framwork.admin.service.UserService;

/**
 * @author levin
 * @since 1.0.0
 */
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
