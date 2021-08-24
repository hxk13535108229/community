package com.hxk.community.dao;

import com.hxk.community.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {

    void insert(Comment comment);
}
