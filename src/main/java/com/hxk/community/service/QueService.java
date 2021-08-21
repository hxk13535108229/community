package com.hxk.community.service;

import com.hxk.community.dao.QuestionMapper;
import com.hxk.community.entity.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName QueService
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-21 21:15
 * @Version 1.0
 **/
@Service
public class QueService {

    @Autowired
    private QuestionMapper questionMapper;

    //统计问题总数
    public Integer count() {
        return questionMapper.count();
    }

    //添加问题
    public void create(Question question) {
        questionMapper.create(question);
    }

    //返回分页列表
    public List<Question> list(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize) {
        return questionMapper.list(currentPage, pageSize);
    }
}
