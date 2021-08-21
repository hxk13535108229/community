package com.hxk.community.dto;

import lombok.Data;

/**
 * @ClassName AccessTokenDTO
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-19 17:09
 * @Version 1.0
 **/
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;

}
