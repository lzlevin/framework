package com.vin.framework.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.vin.framework.core.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 带序号的基础抽象实体
 *
 * @param <PK>
 */
@Data
public abstract class AbstractBaseEntity<PK extends Serializable> implements BaseEntity<PK> {

    /**
     * 主键
     */
    @TableId
    private PK id;
    /**
     * 逻辑删除标识
     */
    @TableField(fill = FieldFill.INSERT, select = false)
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String modifyBy;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifyTime;
}
