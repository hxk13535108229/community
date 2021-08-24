package com.hxk.community.entity;

import lombok.Data;

/**
 * @ClassName Comment
 * @Description 评论实体类
 * @Author OvO
 * @Date 2021-08-24 16:15
 * @Version 1.0
 **/
@Data
public class Comment {
    private Long id;

    private Long parent_id;

    private Long gmt_create;

    private Long gmt_modified;

    private Long like_count;

    private String comment_CId;// 评论者id

    private String content;

    private Integer type;
}
