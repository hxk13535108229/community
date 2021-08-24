package com.hxk.community.service;

import com.hxk.community.dao.CommentMapper;
import com.hxk.community.dao.QuestionMapper;
import com.hxk.community.entity.Comment;
import com.hxk.community.entity.Question;
import com.hxk.community.enums.CommentTypeEnum;
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
    private QuestionMapper questionMapper;

    @Autowired
    private CommentMapper commentMapper;

    public void insert(Comment comment) {
        //父类id不存在
        if (comment.getParent_id() == null || comment.getParent_id() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        //评论类型不存在
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_TYPE_ERROR);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            //通过父类id查出父类评论
            Comment parentComment = commentMapper.selFromPId(comment.getParent_id());
            if (parentComment == null) {//不存在这个父类评论的话
                throw new CustomizeException(CustomizeErrorCode.COMMEMT_NOT_FOUND);
            } else {
                commentMapper.insert(comment);
            }
        }
        if (comment.getType() == CommentTypeEnum.QUESTION.getType()) {
            //回复问题
            //通过父类id查出父类问题
            Question parentQuestion = questionMapper.findById(comment.getParent_id());
            if (parentQuestion == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            } else {
                commentMapper.insert(comment);
                //增加评论数
                parentQuestion.setComment_count(parentQuestion.getComment_count() + 1);
                //不让点赞数和阅读数增加
                parentQuestion.setView_count(null);
                parentQuestion.setLike_count(null);
                questionMapper.update(parentQuestion);
            }
        }
    }
}
