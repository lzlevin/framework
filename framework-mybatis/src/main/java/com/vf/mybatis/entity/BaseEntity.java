package com.vf.mybatis.entity;

import com.vf.common.entity.Entity;
import com.vf.common.entity.Idable;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体，具备常用删除标志位、创建时间、创建人、修改时间、修改人
 *
 * @param <PK> 主键类型
 * @author levin
 */
public interface BaseEntity<PK extends Serializable> extends Entity<PK>, Idable<PK> {
    /**
     * 逻辑删除标识
     *
     * @return 逻辑删除标识
     */
    Integer getDeleted();

    /**
     * 创建人
     *
     * @return 创建人
     */
    String getCreateBy();

    /**
     * 创建时间
     *
     * @return 创建时间
     */
    LocalDateTime getCreateTime();

    /**
     * 修改人
     *
     * @return 修改人
     */
    String getModifyBy();

    /**
     * 修改时间
     *
     * @return 修改时间
     */
    LocalDateTime getModifyTime();
}
