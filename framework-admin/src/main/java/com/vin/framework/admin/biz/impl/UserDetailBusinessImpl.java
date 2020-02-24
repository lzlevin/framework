package com.vin.framework.admin.biz.impl;

import com.vin.framework.admin.entity.UserDetailEntity;
import com.vin.framework.admin.mapper.UserDetailMapper;
import com.vin.framework.mybatis.service.impl.ServiceImpl;
import com.vin.framework.admin.biz.UserDetailBusiness;
import org.springframework.stereotype.Service;

/**
 * @author levin
 * @since 1.0.0
 */
@Service
public class UserDetailBusinessImpl extends ServiceImpl<UserDetailMapper, UserDetailEntity> implements UserDetailBusiness {
}
