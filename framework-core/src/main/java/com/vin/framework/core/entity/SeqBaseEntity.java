package com.vin.framework.core.entity;

import java.io.Serializable;

/**
 * 带序号的基础实体接口
 * @param <PK>
 */
public interface SeqBaseEntity<PK extends Serializable> extends BaseEntity<PK> {
    /**
     * 获取序号
     * @return 序号
     */
    Integer getSeq();
}
