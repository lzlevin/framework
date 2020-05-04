package com.vf.admin.vo;

import com.vf.common.entity.Parent;
import com.vf.mvc.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SysOrgEntityVO", description = "组织机构")
public class SysOrgVO extends BaseVO<String> implements Serializable, Parent<String> {


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

    private Boolean useFlag;

    /**
     * 孩子节点
     */
    @ApiModelProperty(value = "孩子节点")
    private List<SysOrgVO> children;
}
