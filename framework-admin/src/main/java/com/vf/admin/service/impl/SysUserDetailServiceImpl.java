package com.vf.admin.service.impl;

import com.vf.admin.entity.SysUserDetailEntity;
import com.vf.admin.service.SysUserDetailService;
import com.vf.admin.biz.SysUserDetailBiz;
import com.vf.mybatis.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户详情信息 服务实现类
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Service
public class SysUserDetailServiceImpl implements SysUserDetailService {

    @Autowired
    private SysUserDetailBiz biz;

    /**
    * dao
    *
    * @return E的dao
    */
    @Override
    public <D extends IService<SysUserDetailEntity>> D getDao() {
        return (D) biz;
    }
}
