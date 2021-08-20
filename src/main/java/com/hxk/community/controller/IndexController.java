package com.hxk.community.controller;

import com.hxk.community.dto.QuestionDTO;
import com.hxk.community.mapper.QuestionMapper;
import com.hxk.community.mapper.UserMapper;
import com.hxk.community.model.User;
import com.hxk.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-19 15:09
 * @Version 1.0
 **/
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/")
    public String goIndex(HttpServletRequest httpServletRequest,
                          Model model) {

        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies!=null&&cookies.length!=0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user!=null){
                        httpServletRequest.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<QuestionDTO> questionDTOList = questionService.list();
        model.addAttribute("questionList",questionDTOList);
        return "index";
    }
}
