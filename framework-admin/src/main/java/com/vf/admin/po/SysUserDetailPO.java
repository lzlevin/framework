package com.vf.admin.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 用户详情信息
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserDetailPO implements Serializable {


    /**
     * 用户ID
     */
    private String userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 邮箱是否验证
     */
    private Integer emailVerify;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 手机号是否验证
     */
    private Integer phoneVerify;

    /**
     * 证件类型
     */
    private String certificateType;

    /**
     * 证件号
     */
    private String certificateNo;

    /**
     * 地址
     */
    private String address;

}
