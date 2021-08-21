package com.hxk.community.controller;

import com.hxk.community.dto.PaginationDTO;
import com.hxk.community.entity.User;
import com.hxk.community.service.QuestionService;
import com.hxk.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName IndexController
 * @Description 首页
 * @Author OvO
 * @Date 2021-08-19 15:09
 * @Version 1.0
 **/
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/")
    public String goIndex(HttpServletRequest httpServletRequest,
                          Model model,
                          @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", defaultValue = "4") Integer pageSize
    ) {

        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userService.findByToken(token);
                    if (user != null) {
                        //设置用户session
                        httpServletRequest.getSession().setAttribute("user", user);
//                        PaginationDTO paginationDTO=  questionService.getPaginationDTO(pageNum,pageSize,user);
//                        model.addAttribute("pagination", paginationDTO);
                    }
                    break;
                }
            }
        }
        return "index";
    }
}