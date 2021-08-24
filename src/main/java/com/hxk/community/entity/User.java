package com.hxk.community.entity;

import lombok.Data;

/**
 * @ClassName User
 * @Description 用户实体类
 * @Author OvO
 * @Date 2021-08-19 23:41
 * @Version 1.0
 **/
@Data
public class User {
    private String account_id;      //用户id
    private String account_name;    //用户名字
    private String token;           //accesstoken
    private Long gmt_create;        //创建时间
    private Long gmt_modify;        //修改时间
    private String avatar_url;      //用户图标url
    private Long id;
}
