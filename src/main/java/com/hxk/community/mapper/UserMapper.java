package com.hxk.community.mapper;


import com.hxk.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into user (account_id, account_name, token, gmt_create, gmt_modify,avatar_url) values (#{account_id}," +
            "#{account_name},#{token},#{gmt_create},#{gmt_modify},#{avatar_url})")
    public void insertUser(User user);

    @Select("select * from user where token= #{token}")
    User findByToken(@Param("token") String token);
}
