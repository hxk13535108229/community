package com.hxk.community.dto;

import com.hxk.community.model.User;
import lombok.Data;

/**
 * @ClassName QuestionDTO
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-20 11:19
 * @Version 1.0
 **/
@Data
public class QuestionDTO {
    private Integer id;
    private String title;           //标题
    private String description;     //描述
    private String tag;             //标签
    private Long gmt_create;        //创建时间
    private Long gmt_modify;        //修改时间
    private Integer creator;        //创建者id
    private Integer comment_count;  //评论数
    private Integer view_count;     //观看数
    private Integer like_count;     //点赞数
    private User user;              //关联的用户
}
