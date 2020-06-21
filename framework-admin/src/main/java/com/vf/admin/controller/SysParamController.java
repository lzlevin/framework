package com.vf.admin.controller;

import com.vf.admin.service.SysParamService;
import com.vf.admin.dto.SysParamDTO;
import com.vf.admin.entity.SysParamEntity;
import com.vf.admin.vo.SysParamVO;
import com.vf.admin.po.SysParamPO;
import com.vf.mvc.response.ApiResponse;
import com.vf.mvc.vo.NameVO;
import com.vf.log.annotation.Log;
import com.vf.mvc.controller.CurdController;
import com.vf.mvc.service.CurdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 系统参数表 前端控制器
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/sysParam")
@Log(function = "系统参数表")
public class SysParamController implements CurdController<SysParamVO, SysParamDTO, SysParamEntity, SysParamPO> {

    @Autowired
    private SysParamService service;

    /**
     * curd服务
     *
     * @return curd服务
     */
    @Override
    public <S extends CurdService<SysParamEntity, SysParamDTO, SysParamPO>> S getService() {
        return (S) service;
    }
}
