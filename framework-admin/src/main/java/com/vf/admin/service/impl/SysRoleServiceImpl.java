package com.vf.admin.service.impl;

import com.vf.admin.entity.SysRoleEntity;
import com.vf.admin.service.SysRoleService;
import com.vf.admin.biz.SysRoleBiz;
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
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleBiz biz;

    /**
    * dao
    *
    * @return E的dao
    */
    @Override
    public <D extends IService<SysRoleEntity>> D getDao() {
        return (D) biz;
    }
}
