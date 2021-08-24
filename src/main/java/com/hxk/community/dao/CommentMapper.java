package com.hxk.community.dao;

import com.hxk.community.entity.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface CommentMapper {

    void insert(Comment comment);

    Comment selFromPId(@PathVariable("parent_id") Long parent_id);
}
