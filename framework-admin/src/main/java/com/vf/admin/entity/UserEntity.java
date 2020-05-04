package com.vf.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vf.admin.constant.UserStatus;
import com.vf.admin.constant.UserType;
import com.vf.validate.group.CreateGroup;
import com.vf.mybatis.entity.StringKeyBaseEntity;
import com.vf.validate.constraints.Constant;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 用户实体，只存储基本账号信息，用户详细信息存放在 {@link UserDetailEntity}
 *
 * @author levin
 * @since 1.0.0
 */
@Data
@TableName("sys_user")
public class UserEntity extends StringKeyBaseEntity {

    /**
     * 用户名
     */
    @NotBlank(groups = CreateGroup.class, message = "用户名不能为空")
    private String userName;
    /**
     * 昵称
     */
    @NotBlank(groups = CreateGroup.class, message = "用户昵称不能为空")
    private String nickName;
    /**
     * 头像
     */
    private String logo;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * 用户状态
     */
    @NotNull(groups = CreateGroup.class, message = "用户状态错误")
    @Constant(groups = CreateGroup.class, value = UserStatus.class, message = "用户状态错误")
    private Integer status;
    /**
     * 用户类型（后期可用于扩展用户信息表，比如学生表还对应于student表）
     */
    @NotNull(groups = CreateGroup.class, message = "用户类型错误")
    @Constant(groups = CreateGroup.class, value = UserType.class, message = "用户类型错误")
    private Integer userType;
    /**
     * 注册方式
     */
    private String registerType;
    /**
     * 注册主机（IP等）
     */
    private String registerHost;
    /**
     * 登录次数
     */
    private Integer loginTimes;
    /**
     * 登录失败次数
     */
    private Integer loginFailTimes;
    /**
     * 最后登录主机（IP）
     */
    private String lastLoginHost;
    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

}
