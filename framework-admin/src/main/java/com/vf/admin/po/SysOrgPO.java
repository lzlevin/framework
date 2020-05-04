package com.vf.admin.po;

import com.vf.mvc.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 组织机构
 * </p>
 *
 * @author levin
 * @date 2020-05-04
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SysOrgEntityDTO", description = "组织机构")
public class SysOrgPO extends BaseDTO<String> implements Serializable{


    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID")
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

    private Boolean useFlag;


}
