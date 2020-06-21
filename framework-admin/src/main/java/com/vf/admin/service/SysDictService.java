package com.vf.admin.service;

import com.vf.admin.entity.SysDictEntity;
import com.vf.admin.dto.SysDictDTO;
import com.vf.admin.po.SysDictPO;
import com.vf.mvc.service.CurdService;
/**
* <p>
 * 字典表 服务类
 * </p>
*
* @author levin
* @date 2020-06-21
* @since 1.0.0
*/
public interface SysDictService extends CurdService<SysDictEntity, SysDictDTO, SysDictPO> {

}
