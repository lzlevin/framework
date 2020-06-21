package com.vf.admin.controller;

import com.vf.admin.service.SysUserService;
import com.vf.admin.dto.SysUserDTO;
import com.vf.admin.entity.SysUserEntity;
import com.vf.admin.vo.SysUserVO;
import com.vf.admin.po.SysUserPO;
import com.vf.mvc.response.ApiResponse;
import com.vf.log.annotation.Log;
import com.vf.mvc.controller.CurdController;
import com.vf.mvc.service.CurdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/sysUser")
@Log(function = "用户表")
public class SysUserController implements CurdController<SysUserVO, SysUserDTO, SysUserEntity, SysUserPO> {

    @Autowired
    private SysUserService service;

    /**
     * curd服务
     *
     * @return curd服务
     */
    @Override
    public <S extends CurdService<SysUserEntity, SysUserDTO, SysUserPO>> S getService() {
        return (S) service;
    }
}
