package com.vf.admin.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserPO implements Serializable {


    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
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
    private Integer status;

    /**
     * 用户类型
     */
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
     * 启用
     */
    private Boolean useFlag;

    /**
     * 排序
     */
    private Long seq;

}
