package com.hxk.community.service;

import com.hxk.community.dao.UserMapper;
import com.hxk.community.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description 用户业务类
 * @Author OvO
 * @Date 2021-08-21 21:15
 * @Version 1.0
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    //通过token返回用户
    public User findByToken( String token) {
        return userMapper.findByToken(token);
    }

    //通过account_id查找用户
    public User findById( String account_id){
      return  userMapper.findById(account_id);
    }

    //添加用户或更新用户 每次登录只有token改变
    public void insertOrUpdateUser(User user) {
        String account_id = user.getAccount_id();
        System.out.println(account_id);
        User myUser = findById(account_id);
        if(myUser!=null){
            //更新
            userMapper.update(user.getToken(),user.getAccount_id());
        }else {
            //插入
            userMapper.insertUser(user);
        }
    }
}
