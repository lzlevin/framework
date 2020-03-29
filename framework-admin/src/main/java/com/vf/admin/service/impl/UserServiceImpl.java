package com.vf.admin.service.impl;

import com.vf.admin.biz.UserBusiness;
import com.vf.admin.entity.UserEntity;
import com.vf.admin.service.IUserService;
import com.vf.mybatis.mapper.BaseMapper;
import com.vf.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author levin
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserBusiness userBusiness;

    @Override
    public <D extends ServiceImpl<BaseMapper<UserEntity>, UserEntity, Long>> D getDao() {
        return (D) userBusiness;
    }
}
