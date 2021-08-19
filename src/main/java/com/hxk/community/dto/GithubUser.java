package com.hxk.community.dto;

/**
 * @ClassName GithubUser
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-19 17:31
 * @Version 1.0
 **/

public class GithubUser {
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "id=" + id +
                '}';
    }
}
