package com.hxk.community.service;

import com.hxk.community.dto.PaginationDTO;
import com.hxk.community.dto.QuestionDTO;
import com.hxk.community.mapper.QuestionMapper;
import com.hxk.community.mapper.UserMapper;
import com.hxk.community.model.Question;
import com.hxk.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName QuestionService
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-20 11:19
 * @Version 1.0
 **/
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    public PaginationDTO list(Integer pageNum, Integer pageSize) {


        Integer totalCount=questionMapper.count();
        PaginationDTO paginationDTO = new PaginationDTO();
        if(pageNum<=0){
            pageNum=1;
        }
        Integer totalPages=0;
        if(totalCount%pageSize==0){
            totalPages=totalCount/pageSize;
        }else {
            totalPages=totalCount/pageSize+1;
        }
        if(pageNum>=totalPages){
            pageNum=totalPages;
        }
        paginationDTO.setPaginationDTO(totalCount,pageNum,pageSize);
        Integer currentPage=pageSize*(pageNum-1);
        List<Question> questions = questionMapper.list(currentPage,pageSize);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//拷贝类
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOList);


        return paginationDTO;
    }
}
