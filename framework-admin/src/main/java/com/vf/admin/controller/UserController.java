package com.vf.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vf.admin.biz.UserBusiness;
import com.vf.admin.dto.UserDTO;
import com.vf.admin.entity.UserEntity;
import com.vf.admin.vo.UserVO;
import com.vf.core.api.ApiResponse;
import com.vf.validate.group.CreateGroup;
import com.vf.validate.group.DeleteGroup;
import com.vf.validate.group.UpdateGroup;
import com.vf.validate.validator.BeanValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author levin
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "vin.admin.controller", name = "enabled", havingValue = "true")
public class UserController {

    @Autowired
    private UserBusiness business;

    /**
     * 根据条件查询
     *
     * @param userDTO 条件实体
     * @return api响应
     */
    @RequestMapping("list")
    public ApiResponse list(UserDTO userDTO) {
        log.info("[用户查询开始]，接口名[list]");
        long start = System.currentTimeMillis();
        UserEntity entity = new UserEntity();
        BeanUtil.copyProperties(userDTO, entity);
        List<UserEntity> list = business.list(Wrappers.query(entity));
        List<UserVO> collect = list.stream().map(t -> {
            UserVO po = new UserVO();
            BeanUtil.copyProperties(t, po);
            return po;
        }).collect(Collectors.toList());
        log.info("[用户查询结束]，耗时[{}]毫秒", System.currentTimeMillis() - start);
        return ApiResponse.success(collect);
    }

    /**
     * 分页查询
     *
     * @param dto 分页查询DTO
     * @return
     */
    @RequestMapping("page")
    public ApiResponse page(UserDTO dto) {
        log.info("[用户分页查询开始]，接口名[page]");
        long start = System.currentTimeMillis();
        Page<UserEntity> page = new Page<>();
        page.setCurrent(dto.getCurrent());
        page.setSize(dto.getSize());
        UserEntity condition = new UserEntity();
        BeanUtil.copyProperties(dto, condition);
        Page<UserEntity> result = business.page(page, Wrappers.query(condition));
        Page<UserVO> response = new Page<>();
        BeanUtil.copyProperties(result, response, CopyOptions.create().setIgnoreProperties("records", "optimizeCountSql"));
        List<UserVO> collect = result.getRecords().stream().map(t -> {
            UserVO po = new UserVO();
            BeanUtil.copyProperties(t, po);
            return po;
        }).collect(Collectors.toList());
        response.setRecords(collect);
        log.info("[用户分页查询结束]，耗时[{}]毫秒", System.currentTimeMillis() - start);
        return ApiResponse.success(response);
    }

    /**
     * 根据主键获取
     *
     * @param dto 主键实体类
     * @return
     */
    @RequestMapping("get")
    public ApiResponse get(UserDTO dto) {
        log.info("[用户根据ID查询开始]，接口名[get]");
        long start = System.currentTimeMillis();
        UserVO po = new UserVO();
        UserEntity user = business.getById(dto.getId());
        BeanUtil.copyProperties(user, po);
        log.info("[用户根据ID查询结束]，耗时[{}]毫秒", System.currentTimeMillis() - start);
        return ApiResponse.success(po);
    }

    /**
     * 创建接口
     *
     * @param dto 用户信息
     * @return
     */
    @RequestMapping("create")
    public ApiResponse create(UserDTO dto) {
        log.info("[用户创建开始]，接口名[create]");
        long start = System.currentTimeMillis();
        UserEntity entity = new UserEntity();
        BeanUtil.copyProperties(dto, entity);
        BeanValidator.validate(dto, CreateGroup.class);
        boolean save = business.save(entity);
        log.info("[用户创建结束]，耗时[{}]毫秒", System.currentTimeMillis() - start);
        return ApiResponse.success(save);
    }

    /**
     * 根据主键更新
     *
     * @param dto 用户数据
     * @return
     */
    @RequestMapping("update")
    public ApiResponse update(UserDTO dto) {
        log.info("[用户更新开始]，接口名[update]");
        long start = System.currentTimeMillis();
        UserEntity entity = new UserEntity();
        BeanUtil.copyProperties(dto, entity);
        BeanValidator.validate(entity, UpdateGroup.class);
        boolean save = business.updateById(entity);
        log.info("[用户更新结束]，耗时[{}]毫秒", System.currentTimeMillis() - start);
        return ApiResponse.success(save);
    }

    /**
     * 根据主键删除
     *
     * @param dto 主键
     * @return
     */
    @RequestMapping("delete")
    public ApiResponse delete(UserDTO dto) {
        log.info("[用户根据ID删除开始]，接口名[delete]");
        long start = System.currentTimeMillis();
        UserEntity entity = new UserEntity();
        BeanUtil.copyProperties(dto, entity);
        BeanValidator.validate(entity, DeleteGroup.class);
        boolean save = business.removeById(entity.getId());
        log.info("[用户根据ID删除结束]，耗时[{}]毫秒", System.currentTimeMillis() - start);
        return ApiResponse.success(save);
    }

}
