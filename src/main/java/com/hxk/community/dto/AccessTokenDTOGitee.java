package com.hxk.community.dto;

/**
 * @ClassName AccessTokenDTOGitee
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-19 20:08
 * @Version 1.0
 **/
public class AccessTokenDTOGitee {
    private String code;
    private String redirect_uri;
    private String client_id;
    private String client_secret;

    private String grant_type;

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
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
}
