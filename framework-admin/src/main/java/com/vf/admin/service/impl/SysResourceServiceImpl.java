package com.vf.admin.service.impl;

import com.vf.admin.entity.SysResourceEntity;
import com.vf.admin.service.SysResourceService;
import com.vf.admin.biz.SysResourceBiz;
import com.vf.mybatis.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 万物皆资源 服务实现类
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    private SysResourceBiz biz;

    /**
    * dao
    *
    * @return E的dao
    */
    @Override
    public <D extends IService<SysResourceEntity>> D getDao() {
        return (D) biz;
    }
}
