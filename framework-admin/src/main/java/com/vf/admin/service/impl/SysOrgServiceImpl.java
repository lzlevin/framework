package com.vf.admin.service.impl;

import com.vf.admin.entity.SysOrgEntity;
import com.vf.admin.service.SysOrgService;
import com.vf.admin.biz.SysOrgBiz;
import com.vf.mybatis.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 组织机构 服务实现类
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Service
public class SysOrgServiceImpl implements SysOrgService {

    @Autowired
    private SysOrgBiz biz;

    /**
    * dao
    *
    * @return E的dao
    */
    @Override
    public <D extends IService<SysOrgEntity>> D getDao() {
        return (D) biz;
    }
}
