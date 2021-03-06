package com.hxk.community.controller;

import com.hxk.community.dto.PaginationDTO;
import com.hxk.community.entity.User;
import com.hxk.community.enums.CustomizeErrorCode;
import com.hxk.community.exception.CustomizeException;
import com.hxk.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ProfileController
 * @Description 我的提问展示
 * @Author OvO
 * @Date 2021-08-21 23:59
 * @Version 1.0
 **/
@Controller
public class ProfileController {


    @Autowired
    private QuestionService questionService;

    /*
    我的问题 问题列表
     */
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest httpServletRequest,
                          Model model,
                          @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", defaultValue = "4") Integer pageSize) {

        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/"; //返回首页
        }
        PaginationDTO paginationDTO = questionService.getPaginationDTO(pageNum, pageSize, user);
        model.addAttribute("pagination", paginationDTO);
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        } else {//暂时这样 可能还有扩展
            throw new CustomizeException(CustomizeErrorCode.WEBPAGE_NOT_FOUND);
        }

        return "profile";
    }
}
