package com.hxk.community.advice;

import com.alibaba.fastjson.JSON;
import com.hxk.community.dto.ResultDTO;
import com.hxk.community.enums.CustomizeErrorCode;
import com.hxk.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName CustomizeExceptionHandler
 * @Description 自定义异常处理
 * @Author OvO
 * @Date 2021-08-23 15:13
 * @Version 1.0
 **/
@ControllerAdvice
public class CustomizeExceptionHandler extends ResponseEntityExceptionHandler {

    /*
    判断请求的类型 评论回复 or 其他
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Throwable ex, Model model, HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
        String contentType = httpServletRequest.getContentType();
        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO;
            if (ex instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException) ex);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            PrintWriter writer;
            try {
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.setContentType("application/json");
                writer = httpServletResponse.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException e) {
                // e.printStackTrace(); 后面再处理
            }

        } else {
            if (ex instanceof CustomizeException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
        }
        return new ModelAndView("error");
    }
}
