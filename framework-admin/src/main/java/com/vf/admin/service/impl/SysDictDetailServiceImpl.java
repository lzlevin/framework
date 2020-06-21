package com.vf.admin.service.impl;

import com.vf.admin.entity.SysDictDetailEntity;
import com.vf.admin.service.SysDictDetailService;
import com.vf.admin.biz.SysDictDetailBiz;
import com.vf.mybatis.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典详情表 服务实现类
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Service
public class SysDictDetailServiceImpl implements SysDictDetailService {

    @Autowired
    private SysDictDetailBiz biz;

    /**
    * dao
    *
    * @return E的dao
    */
    @Override
    public <D extends IService<SysDictDetailEntity>> D getDao() {
        return (D) biz;
    }
}
