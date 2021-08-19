package com.hxk.community.dto;

/**
 * @ClassName GitUser
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-19 21:55
 * @Version 1.0
 **/
public class GitUser {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GitUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
