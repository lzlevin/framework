package com.vf.admin.vo;

import com.vf.mvc.vo.BaseVO;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vf.mybatis.entity.AbstractBaseEntity;
import com.vf.common.entity.Parent;
import com.vf.common.entity.ChildrenAware;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

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
public class SysResourceVO extends BaseVO<String> implements Serializable, Parent<String>, ChildrenAware<SysResourceVO>{


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

    /**
     * 孩子节点
     */
    @Accessors(chain = false)
    private List<SysResourceVO> children;
}
