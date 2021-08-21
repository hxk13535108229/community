package com.hxk.community.controller;

import com.hxk.community.dto.PaginationDTO;
import com.hxk.community.entity.User;
import com.hxk.community.service.QuestionService;
import com.hxk.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ProfileController
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-21 23:59
 * @Version 1.0
 **/
@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          HttpServletRequest httpServletRequest,
                          Model model,
                          @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", defaultValue = "4") Integer pageSize){
        User user = null;
        System.out.println(pageNum);
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies!=null&&cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userService.findByToken(token);
                    if (user != null) {
                        httpServletRequest.getSession().setAttribute("user", user);
                        PaginationDTO paginationDTO=  questionService.getPaginationDTO(pageNum,pageSize,user);
                        model.addAttribute("pagination", paginationDTO);
                    }
                    break;
                }
            }
        }
       if("questions".equals(action)){
           model.addAttribute("section", "questions");
           model.addAttribute("sectionName", "我的提问");
       }else if("replies".equals(action)){
           model.addAttribute("section", "replies");
           model.addAttribute("sectionName", "最新回复");
       }
        return "profile";
    }
}
