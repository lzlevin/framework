package com.vf.admin.controller;

import com.vf.admin.service.SysUserDetailService;
import com.vf.admin.dto.SysUserDetailDTO;
import com.vf.admin.entity.SysUserDetailEntity;
import com.vf.admin.vo.SysUserDetailVO;
import com.vf.admin.po.SysUserDetailPO;
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
 * 用户详情信息 前端控制器
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/sysUserDetail")
@Log(function = "用户详情信息")
public class SysUserDetailController implements CurdController<SysUserDetailVO, SysUserDetailDTO, SysUserDetailEntity, SysUserDetailPO> {

    @Autowired
    private SysUserDetailService service;

    /**
     * curd服务
     *
     * @return curd服务
     */
    @Override
    public <S extends CurdService<SysUserDetailEntity, SysUserDetailDTO, SysUserDetailPO>> S getService() {
        return (S) service;
    }
}
