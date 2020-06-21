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
public class SysResourceDTO extends BaseDTO<String> implements Serializable{


    /**
     * 父ID
     */
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
    private Boolean useFlag;

    /**
     * 排序
     */
    private Long seq;


}
