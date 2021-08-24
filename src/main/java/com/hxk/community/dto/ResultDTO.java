package com.hxk.community.dto;

import com.hxk.community.exception.CustomizeErrorCode;
import lombok.Data;

/**
 * @ClassName ResultDTO
 * @Description 返回结果DTO
 * @Author OvO
 * @Date 2021-08-24 17:38
 * @Version 1.0
 **/
@Data
public class ResultDTO {
    private Integer code;//状态码

    private String message;

    public static ResultDTO errorOf(Integer code,String message){

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static Object okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("成功");
        return resultDTO;
    }
}
