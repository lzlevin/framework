package com.vf.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vf.common.entity.Name;
import com.vf.common.entity.Parent;
import com.vf.common.entity.Sequence;
import com.vf.common.entity.UseFlag;
import com.vf.mybatis.entity.AbstractBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 组织机构
 * </p>
 *
 * @author levin
 * @date 2020-05-04
 * @since 1.0.0
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_org")
@ApiModel(value = "SysOrgEntity对象", description = "组织机构")
public class SysOrgEntity extends AbstractBaseEntity<String> implements Name, Parent<String>, UseFlag, Sequence {

    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID")
    @Accessors(chain = false)
    private String parentId;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String contact;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 证件类型
     */
    @ApiModelProperty(value = "证件类型")
    private String certificateType;

    /**
     * 证件号
     */
    @ApiModelProperty(value = "证件号")
    private String certificateNo;

    /**
     * 组织类型
     */
    @ApiModelProperty(value = "组织类型")
    private String orgType;

    /**
     * 组织行业
     */
    @ApiModelProperty(value = "组织行业")
    private String orgIndustry;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    private Long seq;

    @Accessors(chain = false)
    private Boolean useFlag;


}
