package com.vf.admin.service.impl;

import com.vf.admin.entity.SysParamEntity;
import com.vf.admin.service.SysParamService;
import com.vf.admin.biz.SysParamBiz;
import com.vf.mybatis.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统参数表 服务实现类
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Service
public class SysParamServiceImpl implements SysParamService {

    @Autowired
    private SysParamBiz biz;

    /**
    * dao
    *
    * @return E的dao
    */
    @Override
    public <D extends IService<SysParamEntity>> D getDao() {
        return (D) biz;
    }
}
