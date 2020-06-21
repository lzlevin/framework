package com.vf.admin.service.impl;

import com.vf.admin.entity.SysRoleGrantEntity;
import com.vf.admin.service.SysRoleGrantService;
import com.vf.admin.biz.SysRoleGrantBiz;
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
public class SysRoleGrantServiceImpl implements SysRoleGrantService {

    @Autowired
    private SysRoleGrantBiz biz;

    /**
    * dao
    *
    * @return E的dao
    */
    @Override
    public <D extends IService<SysRoleGrantEntity>> D getDao() {
        return (D) biz;
    }
}
