package com.vf.admin.vo;

import com.vf.mvc.vo.BaseVO;
import lombok.Data;

/**
 * @author levin
 * @since 1.0.0
 */
@Data
public class UserVO extends BaseVO<Long> {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String logo;
    /**
     * 盐
     */
    private String salt;
    /**
     * 用户状态
     */
    private Integer status;
    /**
     * 用户类型（后期可用于扩展用户信息表，比如学生表还对应于student表）
     */
    private Integer userType;
    /**
     * 登录失败次数
     */
    private Integer loginFailTimes;
}
