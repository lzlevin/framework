package com.vf.mvc.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用业务响应
 *
 * @author levin
 * @since 1.0.0
 */
@Data
public class ApiResponse implements Serializable {

    /**
     * 请求是否成功，只有状态为200时为成功
     */
    private Boolean success;
    /**
     * 响应状态码
     */
    private Integer status;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private Object data;
    /**
     * 时间戳
     */
    private long timestamp;

    private ApiResponse() {
        this.timestamp = System.currentTimeMillis();
    }

    public ApiResponse(Boolean success, Integer status, String message, Object data) {
        this();
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse success() {
        return success(null);
    }

    public static ApiResponse success(Object data) {
        ApiResponseStatus ok = ApiResponseStatus.OK;
        return new ApiResponse(true, ok.getKey(), ok.getValue(), data);
    }

    public static ApiResponse error(String msg) {
        return new ApiResponse(false, ApiResponseStatus.OTHER_ERROR.getKey(), msg, null);
    }

    public static ApiResponse error(ApiResponseStatus status) {
        return new ApiResponse(ApiResponseStatus.OK.equals(status), status.getKey(), status.getValue(), null);
    }
}
