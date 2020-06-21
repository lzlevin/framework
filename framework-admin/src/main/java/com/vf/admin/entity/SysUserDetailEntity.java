package com.vf.admin.entity;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vf.admin.constant.CertificateType;
import com.vf.admin.constant.Gender;
import com.vf.common.constant.RegexConstant;
import com.vf.mybatis.entity.AbstractBaseEntity;
import com.vf.validate.constraints.Constant;
import com.vf.validate.constraints.Enumerable;
import com.vf.validate.constraints.Function;
import com.vf.validate.group.CreateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user_detail")
public class SysUserDetailEntity extends AbstractBaseEntity<String> {


    /**
     * 用户ID
     */
    @NotBlank(groups = CreateGroup.class)
    private String userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    @Constant(value = Gender.class, message = "性别编码错误")
    private Integer gender;

    /**
     * 生日
     */
    @Past(message = "生日不能在当前日期之后")
    private LocalDate birthday;

    /**
     * 邮箱
     */
    @Email(message = "请输入正确的邮箱地址")
    private String email;

    /**
     * 邮箱是否验证
     */
    private Integer emailVerify;

    /**
     * 手机号
     */
    @Pattern(regexp = RegexConstant.CN_PHONE, message = "请输入正确的手机号码")
    private String phone;

    /**
     * 手机号是否验证
     */
    private Integer phoneVerify;

    /**
     * 证件类型
     */
    @Enumerable(value = CertificateType.class, message = "证件类型错误")
    private String certificateType;

    /**
     * 证件号
     */
    @Function("validateCertificateNo")
    private String certificateNo;

    /**
     * 地址
     */
    private String address;

    /**
     * 验证证件信息
     *
     * @return 是否验证通过
     */
    private boolean validateCertificateNo() {
        if (CertificateType.SFZ.getKey().equals(this.getCertificateType())) {
            return Validator.isCitizenId(this.certificateNo);
        }
        return true;
    }
}
