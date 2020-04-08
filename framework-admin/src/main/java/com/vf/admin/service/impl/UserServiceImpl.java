package com.vf.admin.service.impl;

import com.vf.admin.biz.UserBusiness;
import com.vf.admin.dto.UserDTO;
import com.vf.admin.entity.UserEntity;
import com.vf.admin.po.UserPO;
import com.vf.admin.service.IUserService;
import com.vf.mybatis.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author levin
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserBusiness userBusiness;

    /**
     * dao
     *
     * @return E的dao
     */
    @Override
    public <D extends IService<UserEntity>> D getDao() {
        return (D) userBusiness;
    }

    @Override
    public Class<UserEntity> getClazzEntity() {
        return UserEntity.class;
    }

    /**
     * 获取DTO的class
     *
     * @return DTO的class
     */
    @Override
    public Class<UserDTO> getClassDTO() {
        return UserDTO.class;
    }

    /**
     * 获取PO的class
     *
     * @return PO的class
     */
    @Override
    public Class<UserPO> getClassPO() {
        return UserPO.class;
    }
}
