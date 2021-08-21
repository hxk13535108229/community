package com.hxk.community.dto;

import lombok.Data;

/**
 * @ClassName GiteeAccessToken
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-19 20:37
 * @Version 1.0
 **/
@Data
public class GiteeAccessToken {
    private String access_token;
    private String token_type;
    private Long expires_in;
    private String refresh_token;
    private String scope;
}
