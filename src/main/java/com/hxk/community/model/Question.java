package com.hxk.community.model;

import lombok.Data;

/**
 * @ClassName Question
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-20 1:56
 * @Version 1.0
 **/
@Data
public class Question {
    private Integer id;
    private String title;           //标题
    private String description;     //描述
    private String tag;             //标签
    private Long gmt_create;        //创建时间
    private Long gmt_modify;        //修改时间
    private String  account_id;      //创建者id
    private Integer comment_count;  //评论数
    private Integer view_count;     //观看数
    private Integer like_count;     //点赞数
}
