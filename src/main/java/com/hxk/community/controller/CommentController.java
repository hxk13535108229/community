package com.hxk.community.controller;

import com.hxk.community.dto.CommentCreateDTO;
import com.hxk.community.dto.ResultDTO;
import com.hxk.community.entity.Comment;
import com.hxk.community.entity.User;
import com.hxk.community.enums.CustomizeErrorCode;
import com.hxk.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CommentController
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-24 14:50
 * @Version 1.0
 **/
@Controller
public class CommentController {

    @Autowired

    private CommentService commentService;

    //使用json的方式
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGIN);
        }
        if(commentCreateDTO==null|| StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParent_id(commentCreateDTO.getParent_id());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmt_create(System.currentTimeMillis());
        comment.setGmt_modified(comment.getGmt_create());
        comment.setComment_CId(user.getAccount_id());
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
