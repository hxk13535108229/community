package com.hxk.community.dao;


import com.hxk.community.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    //添加用户
   void insertUser(User user);

   //通过token返回用户
    User findByToken(@Param("token") String token);

    //通过token和accient_id返回用户
    User findById(@Param("account_id")String account_id,@Param("token") String token);
}
