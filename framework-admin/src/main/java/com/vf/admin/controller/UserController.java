package com.vf.admin.controller;

import com.vf.admin.dto.UserDTO;
import com.vf.admin.entity.UserEntity;
import com.vf.admin.service.IUserService;
import com.vf.admin.vo.UserVO;
import com.vf.mvc.controller.CurdController;
import com.vf.mvc.service.CurdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author levin
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "vin.admin.controller", name = "enabled", havingValue = "true")
public class UserController implements CurdController<UserVO, UserDTO, UserEntity> {

    @Autowired
    private IUserService service;

    @Override
    public <S extends CurdService<UserEntity>> S getService() {
        return (S) service;
    }
}
