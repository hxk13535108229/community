package com.hxk.community.advice;

import com.hxk.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CustomizeExceptionHandler
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-23 15:13
 * @Version 1.0
 **/
@ControllerAdvice
public class CustomizeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Throwable ex, Model model) {
        if(ex instanceof CustomizeException){
            model.addAttribute("message", ex.getMessage());
        }else {
            model.addAttribute("message", "服务器冒烟了!!!");
        }
        return new ModelAndView("error");
    }


}
