package com.vf.admin.controller;

import com.vf.admin.service.SysRoleService;
import com.vf.admin.dto.SysRoleDTO;
import com.vf.admin.entity.SysRoleEntity;
import com.vf.admin.vo.SysRoleVO;
import com.vf.admin.po.SysRolePO;
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
 *  前端控制器
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/sysRole")
@Log(function = "")
public class SysRoleController implements CurdController<SysRoleVO, SysRoleDTO, SysRoleEntity, SysRolePO> {

    @Autowired
    private SysRoleService service;

    /**
     * curd服务
     *
     * @return curd服务
     */
    @Override
    public <S extends CurdService<SysRoleEntity, SysRoleDTO, SysRolePO>> S getService() {
        return (S) service;
    }
}
