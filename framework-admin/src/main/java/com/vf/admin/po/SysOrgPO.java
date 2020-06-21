package com.vf.admin.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vf.mybatis.entity.AbstractBaseEntity;
import com.vf.common.entity.Parent;
import com.vf.common.entity.ChildrenAware;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 组织机构
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysOrgPO implements Serializable {


    /**
     * 父ID
     */
    @Accessors(chain = false)
    private String parentId;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 拼音简写
     */
    private String pinyinShort;

    /**
     * 拼音全场
     */
    private String pinyinLong;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 证件类型
     */
    private String certificateType;

    /**
     * 证件号
     */
    private String certificateNo;

    /**
     * 组织类型
     */
    private String orgType;

    /**
     * 组织行业
     */
    private String orgIndustry;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Long seq;

    /**
     * 启用
     */
    private Boolean useFlag;

    /**
     * 孩子节点
     */
    private List<SysOrgPO> children;
}
