package com.hxk.community.dao;


import com.hxk.community.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    //添加用户
   void insertUser(User user);

   //通过token返回用户
    User findByToken(@Param("token") String token);

    User findById(@Param("account_id") String account_id);

    void update(@Param("token") String newtoken,@Param("account_id") String account_id);
}
