package com.vf.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vf.mybatis.entity.AbstractBaseEntity;
import com.vf.common.entity.Name;
import com.vf.common.entity.UseFlag;
import com.vf.common.entity.Sequence;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统参数表
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_param")
public class SysParamEntity extends AbstractBaseEntity<String> implements Name, UseFlag, Sequence {


    /**
     * 参数编码
     */
    private String code;

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数值
     */
    private String value;

    /**
     * 参数类型
     */
    private String type;

    /**
     * 启用
     */
    @Accessors(chain = false)
    private Boolean useFlag;

    /**
     * 排序
     */
    private Long seq;


}
