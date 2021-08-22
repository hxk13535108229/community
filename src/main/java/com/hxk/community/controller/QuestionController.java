package com.hxk.community.controller;

import com.hxk.community.dto.QuestionDTO;
import com.hxk.community.entity.User;
import com.hxk.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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

    /*
    展示问题详情
     */
    @GetMapping("/question/{id}")
    public String goQuestion(@PathVariable(name ="id" )Integer id,
                             HttpServletRequest httpServletRequest,
                             Model model){
        //获取当前登录用户
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        QuestionDTO questionDTO = questionService.findByQuestionId(id, user);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
