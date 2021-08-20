package com.hxk.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PaginationDTO
 * @Description TODO
 * @Author OvO
 * @Date 2021-08-20 17:05
 * @Version 1.0
 **/
@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;
    private boolean showPrePage;//前一页
    private boolean showNextPage;//下一页
    private boolean hasFirstPage;//第一页
    private boolean hasLastPage;//最后一页

    private Integer currentPage;//当前页
    private List<Integer> pages = new ArrayList<>();

    public void setPaginationDTO(Integer totalCount, Integer pageNum, Integer pageSize) {
        Integer totalPages = 0; //总页数
        if (totalPages % pageSize == 0) {
            totalPages = totalPages / pageSize;
        } else {
            totalPages = totalPages / pageSize + 1;
        }

        //是否展示上一页
        if (currentPage == 1) {
            showPrePage = false;
        } else {
            showPrePage = true;
        }

        //是否展示下一页
        if (currentPage == totalPages) {
            showNextPage = false;
        } else {
            showNextPage = true;
        }

        //是否展示首页和尾页
        if(pages.contains(1)){
            hasFirstPage=false;
        }else {
            hasFirstPage=true;
        }
        if(pages.contains(totalPages)){
            hasLastPage=false;
        }else {
            hasLastPage=true;
        }


    }
}
