package com.hxk.community.controller;

import com.hxk.community.dto.QuestionDTO;
import com.hxk.community.entity.Question;
import com.hxk.community.entity.User;
import com.hxk.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName PublishController
 * @Description 发布页面
 * @Author OvO
 * @Date 2021-08-20 1:13
 * @Version 1.0
 **/
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    /*
    编辑问题
     */
    @GetMapping("/publish/{id}")
    public String updatePublish(@PathVariable(name = "id") Long id,
                                HttpServletRequest httpServletRequest,
                                Model model) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        QuestionDTO questionDTO = questionService.findByQuestionId(id, user);
        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("tag", questionDTO.getDescription());
        model.addAttribute("id", id);//添加问题唯一标志
        return "publish";
    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    /*
    发布问题
     */
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam("id") Long id,
            HttpServletRequest httpServletRequest,
            Model model
    ) {
        //出错时文本的保留
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        //空白文本验证
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录,请登录再进行下一步操作");
            return "publish";
        }
        Question question = new Question();
        question.setTag(tag);
        question.setDescription(description);
        question.setTitle(title);
        question.setAccount_id(user.getAccount_id());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modify(question.getGmt_create());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
