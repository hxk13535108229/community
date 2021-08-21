package com.hxk.community.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxk.community.dao.QuestionMapper;
import com.hxk.community.dto.PaginationDTO;
import com.hxk.community.dto.QuestionDTO;
import com.hxk.community.entity.Question;
import com.hxk.community.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName QueService
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-21 21:15
 * @Version 1.0
 **/
@Service
public class QuestionService {

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

    //返回用户id绑定的问题
    public List<Question> listByAccountId(@Param("account_id") String account_id) {
        return questionMapper.listByAccountId(account_id);
    }

    public PaginationDTO getPaginationDTO(Integer pageNum, Integer pageSize, User user) {
        //逻辑 查出所有数据  匹配用户id查出对应的问题  统计问题总数  匹配成功分页显示
        PaginationDTO paginationDTO = new PaginationDTO();
        PageHelper.startPage(pageNum,pageSize);
        //这里要分页
        List<Question> questions = this.listByAccountId(user.getAccount_id());
        PageInfo<Question> pageInfo = new PageInfo<>(questions);
        paginationDTO.setHasPreviousPage(pageInfo.isHasPreviousPage());
        paginationDTO.setHasNextPage(pageInfo.isHasNextPage());
        paginationDTO.setFirstPage(pageInfo.isIsFirstPage());
        paginationDTO.setLastPage(pageInfo.isIsLastPage());
        paginationDTO.setTotalPages(pageInfo.getPages());
        List<QuestionDTO> questionDTOList =new ArrayList<>();
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();//在循环外面的话用add方法会覆盖前面的数据
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOList);
        paginationDTO.setCurrentPage(pageInfo.getPageNum());
        paginationDTO.setPages(paginationDTO.getPagesList());
        return paginationDTO;
    }
}
