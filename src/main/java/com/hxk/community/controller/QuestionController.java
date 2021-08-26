package com.hxk.community.controller;

import com.hxk.community.dto.CommentCreateDTO;
import com.hxk.community.dto.CommentDTO;
import com.hxk.community.dto.QuestionDTO;
import com.hxk.community.entity.User;
import com.hxk.community.service.CommentService;
import com.hxk.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName QuestionController
 * @Description 问题详情
 * @Author OvO
 * @Date 2021-08-22 15:51
 * @Version 1.0
 **/
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    /*
    展示问题详情
     */
    @GetMapping("/question/{id}")
    public String goQuestion(@PathVariable(name = "id") Long id,
                             HttpServletRequest httpServletRequest,
                             Model model) {
        //获取当前登录用户
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        //获取问题 按时间倒叙
        QuestionDTO questionDTO = questionService.findByQuestionId(id);
        List<CommentDTO> commentDTO =commentService.listByQuestionId(id);
        //更新浏览数
        questionService.createViewCount(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments",commentDTO);

        return "question";
    }
}
