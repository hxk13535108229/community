package com.hxk.community.controller;
import com.hxk.community.dto.PaginationDTO;
import com.hxk.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private QuestionService questionService;
    /*
    首页
     */
    @GetMapping("/")
    public String goIndex(Model model,
                          @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", defaultValue = "4") Integer pageSize) {
        PaginationDTO paginationDTO=  questionService.getPaginationDTO(pageNum,pageSize);
        model.addAttribute("pagination", paginationDTO);
        return "index";
    }

}