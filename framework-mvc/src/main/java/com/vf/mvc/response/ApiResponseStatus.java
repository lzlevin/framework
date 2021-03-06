package com.vf.mvc.response;

import com.vf.common.entity.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用业务响应状态
 *
 * @author levin
 * @since 1.0.0
 */
@AllArgsConstructor
public enum ApiResponseStatus implements IEnum<Integer,String> {

    OK(200, "请求成功"),
    BAD_REQUEST(400, "请求错误"),
    UNAUTHORIZED(401, "验证失败"),
    NOT_FOUND(404, "资源不存在"),
    OTHER_ERROR(444, "其他错误"),
    ERROR(500, "系统错误");

    @Getter
    private Integer key;
    @Getter
    private String value;
}
