package com.vf.admin.biz.impl;

import com.vf.admin.biz.UserDetailBusiness;
import com.vf.admin.entity.UserDetailEntity;
import com.vf.admin.mapper.UserDetailMapper;
import com.vf.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author levin
 * @since 1.0.0
 */
@Service
public class UserDetailBusinessImpl extends ServiceImpl<UserDetailMapper, UserDetailEntity> implements UserDetailBusiness {
}
