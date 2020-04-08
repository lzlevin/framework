package com.vf.admin.biz.impl;

import com.vf.mybatis.service.impl.ServiceImpl;
import com.vf.admin.entity.UserEntity;
import com.vf.admin.mapper.UserMapper;
import com.vf.admin.biz.UserBusiness;
import org.springframework.stereotype.Service;

/**
 * @author levin
 * @since 1.0.0
 */
@Service
public class UserBusinessImpl extends ServiceImpl<UserMapper, UserEntity> implements UserBusiness {
}
