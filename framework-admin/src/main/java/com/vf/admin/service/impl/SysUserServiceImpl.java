package com.vf.admin.service.impl;

import com.vf.admin.entity.SysUserEntity;
import com.vf.admin.service.SysUserService;
import com.vf.admin.biz.SysUserBiz;
import com.vf.mybatis.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserBiz biz;

    /**
    * dao
    *
    * @return E的dao
    */
    @Override
    public <D extends IService<SysUserEntity>> D getDao() {
        return (D) biz;
    }
}
