package com.zz.result;

/**
 * @author zz
 * 返回结果状态码
 */
public enum ResultCode {
    /**
     * 200: 请求成功
     * 400: 请求错误
     * 401: 请求授权失败
     * 404: 未找到路径
     * 500: 服务器产生内部错误
     */
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
