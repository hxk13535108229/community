package com.hxk.community.service;

import com.hxk.community.dao.CommentMapper;
import com.hxk.community.dao.QuestionMapper;
import com.hxk.community.dao.UserMapper;
import com.hxk.community.dto.CommentDTO;
import com.hxk.community.entity.Comment;
import com.hxk.community.entity.Question;
import com.hxk.community.entity.User;
import com.hxk.community.enums.CommentTypeEnum;
import com.hxk.community.enums.CustomizeErrorCode;
import com.hxk.community.exception.CustomizeException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @ClassName CommentService
 * @Description 评论业务类
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

    @Autowired
    private UserMapper userMapper;

    //开启事务
    @Transactional
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

    public List<CommentDTO> listByQuestionId(Long id) {

        List<Comment> comments = commentMapper.selFromPIdList(id);
        if (comments==null||comments.size()==0){
            return new ArrayList<>();
        }
        //拿到所有评论者（去重）
        Set<String> commentCreator = comments.stream().map(comment -> comment.getComment_CId()).collect(Collectors.toSet());
        List<String> userAccountId=new ArrayList<>();
        userAccountId.addAll(commentCreator);

        ArrayList<User> users = new ArrayList<>();
        //获取评论者map
        for (String accountId : userAccountId) {
            User user = userMapper.findById(accountId);
            users.add(user);
        }
        Map<String, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getAccount_id(),user -> user));

        List<CommentDTO> commentDTOs = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            String comment_cId = comment.getComment_CId();
            User user = userMap.get(comment.getComment_CId());
            commentDTO.setUser(user);
            return commentDTO;
        }).collect(Collectors.toList());
return commentDTOs;
    }
}
