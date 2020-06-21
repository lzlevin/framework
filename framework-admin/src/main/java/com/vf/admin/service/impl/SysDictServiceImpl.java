package com.vf.admin.service.impl;

import com.vf.admin.entity.SysDictEntity;
import com.vf.admin.service.SysDictService;
import com.vf.admin.biz.SysDictBiz;
import com.vf.mybatis.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Service
public class SysDictServiceImpl implements SysDictService {

    @Autowired
    private SysDictBiz biz;

    /**
    * dao
    *
    * @return E的dao
    */
    @Override
    public <D extends IService<SysDictEntity>> D getDao() {
        return (D) biz;
    }
}
