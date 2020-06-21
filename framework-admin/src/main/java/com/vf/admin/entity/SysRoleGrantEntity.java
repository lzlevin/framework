package com.vf.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vf.mybatis.entity.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role_grant")
public class SysRoleGrantEntity extends AbstractBaseEntity<String>  {


    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 资源ID
     */
    private String resourceId;

    /**
     * 操作类型
     */
    private String operationType;


}
