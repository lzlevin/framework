package com.vf.admin.controller;

import com.vf.admin.service.SysRoleGrantService;
import com.vf.admin.dto.SysRoleGrantDTO;
import com.vf.admin.entity.SysRoleGrantEntity;
import com.vf.admin.vo.SysRoleGrantVO;
import com.vf.admin.po.SysRoleGrantPO;
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
@RequestMapping("/sysRoleGrant")
@Log(function = "")
public class SysRoleGrantController implements CurdController<SysRoleGrantVO, SysRoleGrantDTO, SysRoleGrantEntity, SysRoleGrantPO> {

    @Autowired
    private SysRoleGrantService service;

    /**
     * curd服务
     *
     * @return curd服务
     */
    @Override
    public <S extends CurdService<SysRoleGrantEntity, SysRoleGrantDTO, SysRoleGrantPO>> S getService() {
        return (S) service;
    }
}
