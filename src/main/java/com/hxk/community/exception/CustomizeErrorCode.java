package com.hxk.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND(2001,"问题已删除或者不存在了噢!"),
    WEBPAGE_NOT_FOUND(2002,"该页面不存在噢!"),
    TARGET_PARAM_NOT_FOUND(2003,"未选中任何问题或评论进行回复"),
    NOT_LOGIN(2004, "未登录不能进行评论噢！");

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
