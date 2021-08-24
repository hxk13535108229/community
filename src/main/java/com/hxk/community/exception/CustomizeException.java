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
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message=errorCode.getMessage();
        this.code=errorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
