package com.vf.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vf.mybatis.entity.AbstractBaseEntity;
import com.vf.common.entity.Name;
import com.vf.common.entity.Parent;
import com.vf.common.entity.UseFlag;
import com.vf.common.entity.Sequence;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 万物皆资源
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_resource")
public class SysResourceEntity extends AbstractBaseEntity<String> implements Name, Parent<String>, UseFlag, Sequence {


    /**
     * 父ID
     */
    @Accessors(chain = false)
    private String parentId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源图标
     */
    private String ico;

    /**
     * 资源类型
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
