package com.hxk.community.exception;

/**
 * @ClassName CustomizeException
 * @Description 自定义异常
 * @Author OvO
 * @Date 2021-08-23 17:21
 * @Version 1.0
 **/
public class CustomizeException extends RuntimeException{

    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getErrorCode();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
