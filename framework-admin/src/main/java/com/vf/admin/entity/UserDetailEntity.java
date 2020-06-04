package com.vf.admin.entity;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vf.admin.constant.CertificateType;
import com.vf.admin.constant.Gender;
import com.vf.validate.group.CreateGroup;
import com.vf.mybatis.entity.LongKeyBaseEntity;
import com.vf.validate.constraints.Constant;
import com.vf.validate.constraints.Enumerable;
import com.vf.validate.constraints.Function;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * 用户详细信息
 *
 * @author levin
 * @since 1.0.0
 */
@Data
@TableName("user_detail")
public class UserDetailEntity extends LongKeyBaseEntity {
    /**
     * 用户ID {@link UserEntity#getId()}
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
    private LocalDateTime birthday;
    /**
     * 邮箱
     */
    @Email(message = "请输入正确的邮箱地址")
    private String email;
    /**
     * 邮箱是否验证
     */
    private Boolean emailVerify;
    /**
     * 手机号
     */
    @Pattern(regexp = "/^[1]([3-9])[0-9]{9}$/", message = "请输入正确的手机号码")
    private String phone;
    /**
     * 手机号是否验证
     */
    private Boolean phoneVerify;
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
