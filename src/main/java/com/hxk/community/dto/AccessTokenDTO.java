package com.hxk.community.dto;

/**
 * @ClassName AccessTokenDTO
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-19 17:09
 * @Version 1.0
 **/
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    private String code;
    private String redirect_uri;

}
