package com.hxk.community.dao;

import com.hxk.community.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface CommentMapper {

    void insert(Comment comment);

    //找到评论
    Comment selFromPId(@Param("parent_id") Long parent_id);

    //找到对应父类id下的所有评论 按时间倒叙
    List<Comment> selFromPIdList(@Param("parent_id") Long parent_id);
}
