package com.vf.admin.vo;

import com.vf.mvc.vo.BaseVO;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vf.mybatis.entity.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

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
public class SysParamVO extends BaseVO<String> implements Serializable{


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
