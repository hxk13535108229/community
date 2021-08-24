package com.hxk.community.dto;

import lombok.Data;

/**
 * @ClassName CommentDTO
 * @Description 评论DTO
 * @Author OvO
 * @Date 2021-08-24 16:34
 * @Version 1.0
 **/
@Data
public class CommentDTO {
    private Long parent_id;

    private String content;

    private Integer type;
}
