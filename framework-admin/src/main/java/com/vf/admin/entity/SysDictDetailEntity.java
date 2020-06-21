package com.vf.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vf.mybatis.entity.AbstractBaseEntity;
import com.vf.common.entity.Name;
import com.vf.common.entity.Sequence;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典详情表
 * </p>
 *
 * @author levin
 * @date 2020-06-21
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict_detail")
public class SysDictDetailEntity extends AbstractBaseEntity<String> implements Name, Sequence {


    /**
     * 字典表
     */
    private String dictId;

    /**
     * 字典项编码
     */
    private String code;

    /**
     * 字典项名称
     */
    private String name;

    /**
     * 排序
     */
    private Long seq;


}
