package com.hxk.community.dto;

import com.hxk.community.entity.User;
import lombok.Data;

/**
 * @ClassName CommentDTO
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-26 18:28
 * @Version 1.0
 **/
@Data
public class CommentDTO {
    private  Long id;
    private Long parent_id;
    private Integer type;
    private String comment_CId;
    private String content;
    private Long gmt_create;
    private Long gmt_modified;
    private Long like_count;
    private User user;
}
