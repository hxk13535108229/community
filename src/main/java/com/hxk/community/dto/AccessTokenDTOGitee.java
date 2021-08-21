package com.hxk.community.dto;

import lombok.Data;

/**
 * @ClassName AccessTokenDTOGitee
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-19 20:08
 * @Version 1.0
 **/
@Data
public class AccessTokenDTOGitee {
    private String code;
    private String redirect_uri;
    private String client_id;
    private String client_secret;
    private String grant_type;
}
