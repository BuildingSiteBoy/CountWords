package com.zz.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zz
 */
@Data
@AllArgsConstructor
public class Result {
    /**
     * code:响应码
     * message:响应传递的信息
     * result：响应传递的对象
     */
    private int code;
    private String message;
    private Object result;
}
