package com.vf.admin.controller;

import com.vf.admin.dto.SysDictDTO;
import com.vf.admin.entity.SysDictEntity;
import com.vf.admin.po.SysDictPO;
import com.vf.admin.service.SysDictService;
import com.vf.admin.vo.SysDictVO;
import com.vf.log.annotation.Log;
import com.vf.mvc.controller.CurdController;
import com.vf.mvc.service.CurdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/sysDict")
@Log(function = "字典表")
public class SysDictController implements CurdController<SysDictVO, SysDictDTO, SysDictEntity, SysDictPO> {

    @Autowired
    private SysDictService service;

    /**
     * curd服务
     *
     * @return curd服务
     */
    @Override
    public <S extends CurdService<SysDictEntity, SysDictDTO, SysDictPO>> S getService() {
        return (S) service;
    }
}
