package com.vf.admin.service.impl;

import com.vf.admin.entity.SysUserRoleEntity;
import com.vf.admin.service.SysUserRoleService;
import com.vf.admin.biz.SysUserRoleBiz;
import com.vf.mybatis.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleBiz biz;

    /**
    * dao
    *
    * @return E的dao
    */
    @Override
    public <D extends IService<SysUserRoleEntity>> D getDao() {
        return (D) biz;
    }
}
