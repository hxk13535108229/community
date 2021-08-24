package com.hxk.community.service;

import com.hxk.community.dao.CommentMapper;
import com.hxk.community.entity.Comment;
import com.hxk.community.exception.CustomizeErrorCode;
import com.hxk.community.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CommentService
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-24 16:33
 * @Version 1.0
 **/
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public void insert(Comment comment) {
        if(comment.getParent_id()==null||comment.getParent_id()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        commentMapper.insert(comment);
    }
}
