package com.vf.admin.biz.impl;

import com.vf.admin.entity.SysUserEntity;
import com.vf.admin.mapper.SysUserMapper;
import com.vf.admin.biz.SysUserBiz;
import com.vf.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 Biz实现类
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Service
public class SysUserBizImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserBiz {

}
