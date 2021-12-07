package com.zz.common.exception;

/**
 * @author zz
 */
public class BeanUtilsException extends RuntimeException {
    public BeanUtilsException(String message) {
        super((message));
    }

    public BeanUtilsException(String message, Throwable cause) {
        super(message, cause);
    }
}
