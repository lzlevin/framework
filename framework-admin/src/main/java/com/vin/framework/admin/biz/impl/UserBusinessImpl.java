package com.vin.framework.admin.biz.impl;

import com.vin.framework.mybatis.service.impl.ServiceImpl;
import com.vin.framework.admin.entity.UserEntity;
import com.vin.framework.admin.mapper.UserMapper;
import com.vin.framework.admin.biz.UserBusiness;
import org.springframework.stereotype.Service;

/**
 * @author levin
 * @since 1.0.0
 */
@Service
public class UserBusinessImpl extends ServiceImpl<UserMapper, UserEntity> implements UserBusiness {
}
