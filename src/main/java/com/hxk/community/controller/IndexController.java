package com.hxk.community.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName IndexController
 * @Description 首页
 * @Author OvO
 * @Date 2021-08-19 15:09
 * @Version 1.0
 **/
@Controller
public class IndexController {


    @GetMapping("/")
    public String goIndex(
    ) {
        return "index";
    }
}