package com.zz.result;

public class ResultFactory {
    public static Result buildResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }

    public static Result buildResult(ResultCode resultCode, String message, Object data) {
        return buildResult(resultCode.code, message, data);
    }

    /**
     * 请求成功
     * @param data: 传递的对象
     * @return 200 + message + 对象
     */
    public static Result buildSuccessResult(Object data) {
        return buildResult(ResultCode.SUCCESS, "请求成功", data);
    }

    /**
     * 请求失败
     * @param message: 失败后传递消息
     * @return 400 + message + null
     */
    public static Result buildFailResult(String message) {
        return buildResult(ResultCode.FAIL, message, null);
    }

    /**
     * 请求失败，未授权
     * @param message: 未授权传递消息
     * @return 401 + message + null
     */
    public static Result unauthorizedResult(String message) {
        return buildResult(ResultCode.UNAUTHORIZED, message, null);
    }
}
