package com.vf.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vf.admin.constant.UserStatus;
import com.vf.admin.constant.UserType;
import com.vf.common.entity.Sequence;
import com.vf.mybatis.entity.AbstractBaseEntity;
import com.vf.validate.constraints.Constant;
import com.vf.validate.group.CreateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUserEntity extends AbstractBaseEntity<String> implements Sequence {


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
     * 用户类型
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
    private LocalDate lastLoginTime;
    /**
     * 排序
     */
    private Long seq;


}
