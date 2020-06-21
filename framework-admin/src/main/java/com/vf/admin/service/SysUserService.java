package com.vf.admin.service;

import com.vf.admin.entity.SysUserEntity;
import com.vf.admin.dto.SysUserDTO;
import com.vf.admin.po.SysUserPO;
import com.vf.mvc.service.CurdService;
/**
* <p>
 * 用户表 服务类
 * </p>
*
* @author levin
* @date 2020-06-21
* @since 1.0.0
*/
public interface SysUserService extends CurdService<SysUserEntity, SysUserDTO, SysUserPO> {

}
