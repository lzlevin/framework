package com.vf.admin.controller;

import com.vf.admin.service.SysResourceService;
import com.vf.admin.dto.SysResourceDTO;
import com.vf.admin.entity.SysResourceEntity;
import com.vf.admin.vo.SysResourceVO;
import com.vf.admin.po.SysResourcePO;
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
 * 万物皆资源 前端控制器
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/sysResource")
@Log(function = "万物皆资源")
public class SysResourceController implements CurdController<SysResourceVO, SysResourceDTO, SysResourceEntity, SysResourcePO> {

    @Autowired
    private SysResourceService service;

    /**
     * curd服务
     *
     * @return curd服务
     */
    @Override
    public <S extends CurdService<SysResourceEntity, SysResourceDTO, SysResourcePO>> S getService() {
        return (S) service;
    }
}
