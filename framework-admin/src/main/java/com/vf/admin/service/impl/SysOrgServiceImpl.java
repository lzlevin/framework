package com.vf.admin.service.impl;

import com.vf.admin.biz.SysOrgBusiness;
import com.vf.admin.entity.SysOrgEntity;
import com.vf.admin.po.SysOrgPO;
import com.vf.admin.service.ISysOrgService;
import com.vf.mybatis.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author levin
 * @since 1.0.0
 */
@Service
public class SysOrgServiceImpl implements ISysOrgService {

    @Autowired
    private SysOrgBusiness business;

    /**
     * dao
     *
     * @return E的dao
     */
    @Override
    public <D extends IService<SysOrgEntity>> D getDao() {
        return (D) business;
    }

    @Override
    public Class<SysOrgEntity> getClazzEntity() {
        return SysOrgEntity.class;
    }

    /**
     * 获取PO的class
     *
     * @return PO的class
     */
    @Override
    public Class<SysOrgPO> getClassPO() {
        return SysOrgPO.class;
    }
}
