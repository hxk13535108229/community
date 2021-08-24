package com.hxk.community.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxk.community.dao.QuestionMapper;
import com.hxk.community.dao.UserMapper;
import com.hxk.community.dto.PaginationDTO;
import com.hxk.community.dto.QuestionDTO;
import com.hxk.community.entity.Question;
import com.hxk.community.entity.User;
import com.hxk.community.exception.CustomizeErrorCode;
import com.hxk.community.exception.CustomizeException;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName QuestionService
 * @Description 问题业务类
 * @Author OvO
 * @Date 2021-08-21 21:15
 * @Version 1.0
 **/
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
   private UserMapper userMapper;



    //返回用户id绑定的问题
    public List<Question> listByAccountId( String account_id) {
        return questionMapper.listByAccountId(account_id);
    }

    //问题列表 对应一个具体的用户 展示其所发布的问题
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

    public QuestionDTO findByQuestionId( Long id,User user){
       Question question= questionMapper.findByQuestionId(id,user.getAccount_id());
       if(question==null){
           throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
       }
           QuestionDTO questionDTO = new QuestionDTO();
           BeanUtils.copyProperties(question, questionDTO);
           questionDTO.setUser(user);
           return questionDTO;
    }

    public void createOrUpdate(Question question) {
  if(question.getId()!=null){
      //更新
      questionMapper.update(question);
  }else {
      //添加
      questionMapper.create(question);
  }
    }

    public void createViewCount(Long id) {
        Question updateQuestion = questionMapper.findById(id);
        updateQuestion.setView_count(updateQuestion.getView_count()+1);//viewCount的Sql自己加一 不覆盖
        updateQuestion.setLike_count(null);
        updateQuestion.setComment_count(null);
        questionMapper.update(updateQuestion);
    }

    //首页获取问题列表  所有用户
    public PaginationDTO getPaginationDTO(Integer pageNum, Integer pageSize) {
        PaginationDTO paginationDTO = new PaginationDTO();
        PageHelper.startPage(pageNum,pageSize);
        List<Question> questions = questionMapper.list(pageNum, pageSize);
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
          User user= userMapper.getUser(question.getAccount_id());
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOList);
        paginationDTO.setCurrentPage(pageInfo.getPageNum());
        paginationDTO.setPages(paginationDTO.getPagesList());
        return paginationDTO;
    }

    public QuestionDTO findByQuestionId(Long id) {
        Question question= questionMapper.findById(id);
        if(question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user= userMapper.getUser(question.getAccount_id());
        questionDTO.setUser(user);
        return questionDTO;
    }
}
