package com.hxk.community.dao;

import com.hxk.community.entity.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper {
    //统计问题总数
     Integer count();

     //添加问题
     void create(Question question);

    //返回分页列表
    List<Question> list(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
}
