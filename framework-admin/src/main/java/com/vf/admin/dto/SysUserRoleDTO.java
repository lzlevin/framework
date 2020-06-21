package com.vf.admin.dto;

import com.vf.mvc.dto.BaseDTO;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vf.mybatis.entity.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
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
public class SysUserRoleDTO extends BaseDTO<String> implements Serializable{


    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;


}
