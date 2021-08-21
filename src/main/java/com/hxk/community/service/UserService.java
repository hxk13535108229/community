package com.hxk.community.service;

import com.hxk.community.dao.UserMapper;
import com.hxk.community.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-21 21:15
 * @Version 1.0
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    //添加用户
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    //通过token返回用户
    public User findByToken(@Param("token") String token) {
        return userMapper.findByToken(token);
    }

    //通过token和accient_id返回用户
    public User findById(@Param("account_id") String account_id, @Param("token") String token) {
        return userMapper.findById(account_id, token);
    }
}
