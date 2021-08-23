package com.hxk.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUNT("问题已删除或者不存在了噢!"),
    WEBPAGE_NOT_FOUNT("该页面不存在噢!");

    private String message;

    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getErrorCode() {
        return getMessage();
    }
}
