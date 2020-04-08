package com.vf.admin.service;

import com.vf.admin.dto.UserDTO;
import com.vf.admin.entity.UserEntity;
import com.vf.admin.po.UserPO;
import com.vf.mvc.service.CurdService;

/**
 * @author levin
 * @since 1.0.0
 */
public interface IUserService extends CurdService<UserEntity, UserDTO, UserPO> {
}
