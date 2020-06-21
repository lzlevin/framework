package com.vf.admin.po;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRolePO implements Serializable {


    /**
     * 所属组织机构
     */
    private String orgId;

    /**
     * 启用
     */
    private Boolean useFlag;

    /**
     * 排序
     */
    private Long seq;

}
