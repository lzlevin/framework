package com.vf.admin.biz.impl;

import com.vf.admin.entity.SysUserDetailEntity;
import com.vf.admin.mapper.SysUserDetailMapper;
import com.vf.admin.biz.SysUserDetailBiz;
import com.vf.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户详情信息 Biz实现类
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Service
public class SysUserDetailBizImpl extends ServiceImpl<SysUserDetailMapper, SysUserDetailEntity> implements SysUserDetailBiz {

}
