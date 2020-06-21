package com.vf.admin.controller;

import com.vf.admin.service.SysDictDetailService;
import com.vf.admin.dto.SysDictDetailDTO;
import com.vf.admin.entity.SysDictDetailEntity;
import com.vf.admin.vo.SysDictDetailVO;
import com.vf.admin.po.SysDictDetailPO;
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
 * 字典详情表 前端控制器
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/sysDictDetail")
@Log(function = "字典详情表")
public class SysDictDetailController implements CurdController<SysDictDetailVO, SysDictDetailDTO, SysDictDetailEntity, SysDictDetailPO> {

    @Autowired
    private SysDictDetailService service;

    /**
     * curd服务
     *
     * @return curd服务
     */
    @Override
    public <S extends CurdService<SysDictDetailEntity, SysDictDetailDTO, SysDictDetailPO>> S getService() {
        return (S) service;
    }
}
