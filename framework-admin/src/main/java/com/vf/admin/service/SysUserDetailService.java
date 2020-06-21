package com.vf.admin.service;

import com.vf.admin.entity.SysUserDetailEntity;
import com.vf.admin.dto.SysUserDetailDTO;
import com.vf.admin.po.SysUserDetailPO;
import com.vf.mvc.service.CurdService;
/**
* <p>
 * 用户详情信息 服务类
 * </p>
*
* @author levin
* @date 2020-06-21
* @since 1.0.0
*/
public interface SysUserDetailService extends CurdService<SysUserDetailEntity, SysUserDetailDTO, SysUserDetailPO> {

}
