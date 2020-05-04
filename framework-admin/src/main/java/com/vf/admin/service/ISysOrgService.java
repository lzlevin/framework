package com.vf.admin.service;

import com.vf.admin.dto.SysOrgDTO;
import com.vf.admin.entity.SysOrgEntity;
import com.vf.admin.po.SysOrgPO;
import com.vf.mvc.service.CurdService;

/**
 * 组织服务类
 *
 * @author levin
 * @date 2020/5/4
 * @since 1.0.0
 */
public interface ISysOrgService extends CurdService<SysOrgEntity, SysOrgDTO, SysOrgPO> {
}
