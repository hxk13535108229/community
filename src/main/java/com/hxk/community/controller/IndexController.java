package com.hxk.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxk.community.dto.PaginationDTO;
import com.hxk.community.dto.QuestionDTO;
import com.hxk.community.mapper.QuestionMapper;
import com.hxk.community.mapper.UserMapper;
import com.hxk.community.model.User;
import com.hxk.community.service.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
                          Model model,
                          @RequestParam(name = "pageNum", defaultValue = "2") Integer pageNum,
                          @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize
                          ) {

        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        httpServletRequest.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        PaginationDTO paginationDTO = questionService.list(pageNum, pageSize);
        model.addAttribute("pagination", paginationDTO);
        return "index";
    }
}
