package com.hxk.community.mapper;

import com.hxk.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title, description, gmt_create, gmt_modify, creator, comment_count, view_count, like_count,tag)" +
            " values (#{title},#{description},#{gmt_create},#{gmt_modify},#{creator},#{comment_count},#{view_count},#{like_count},#{tag})")
    public void create(Question question);
}
