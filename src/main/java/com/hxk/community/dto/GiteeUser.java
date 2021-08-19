package com.hxk.community.dto;

/**
 * @ClassName GiteeUser
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-19 20:41
 * @Version 1.0
 **/
public class GiteeUser {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "GiteeUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
