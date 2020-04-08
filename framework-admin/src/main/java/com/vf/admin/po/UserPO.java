package com.vf.admin.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户PO
 *
 * @author levin
 * @since 1.0.0
 */
@Data
public class UserPO {

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
     * 用户类型（后期可用于扩展用户信息表，比如学生表还对应于student表）
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
    private LocalDateTime lastLoginTime;

}
