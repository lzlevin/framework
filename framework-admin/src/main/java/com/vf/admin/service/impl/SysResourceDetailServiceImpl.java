package com.vf.admin.service.impl;

import com.vf.admin.entity.SysResourceDetailEntity;
import com.vf.admin.service.SysResourceDetailService;
import com.vf.admin.biz.SysResourceDetailBiz;
import com.vf.mybatis.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源详情表 服务实现类
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Service
public class SysResourceDetailServiceImpl implements SysResourceDetailService {

    @Autowired
    private SysResourceDetailBiz biz;

    /**
    * dao
    *
    * @return E的dao
    */
    @Override
    public <D extends IService<SysResourceDetailEntity>> D getDao() {
        return (D) biz;
    }
}
