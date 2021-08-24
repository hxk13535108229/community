package com.hxk.community.enums;

/*
评论类型枚举类
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    /*
    查找有没有这个type
     */
    public static boolean isExist(Integer type) {
        CommentTypeEnum[] commentTypeEnums = CommentTypeEnum.values();
        for (CommentTypeEnum commentTypeEnum : commentTypeEnums) {
            if (commentTypeEnum.getType() == type) {
                return true;
            }
        }
        return false;
    }
}
