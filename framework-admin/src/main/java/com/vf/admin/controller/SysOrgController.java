package com.vf.admin.controller;

import com.vf.admin.service.SysOrgService;
import com.vf.admin.dto.SysOrgDTO;
import com.vf.admin.entity.SysOrgEntity;
import com.vf.admin.vo.SysOrgVO;
import com.vf.admin.po.SysOrgPO;
import com.vf.mvc.response.ApiResponse;
import com.vf.common.entity.Parent;
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
 * 组织机构 前端控制器
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/sysOrg")
@Log(function = "组织机构")
public class SysOrgController implements CurdController<SysOrgVO, SysOrgDTO, SysOrgEntity, SysOrgPO> {

    @Autowired
    private SysOrgService service;

    /**
     * curd服务
     *
     * @return curd服务
     */
    @Override
    public <S extends CurdService<SysOrgEntity, SysOrgDTO, SysOrgPO>> S getService() {
        return (S) service;
    }
}
