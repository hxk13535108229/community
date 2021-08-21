package com.hxk.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PaginationDTO
 * @Description 问题分页页面
 * @Author OvO
 * @Date 2021-08-20 17:05
 * @Version 1.0
 **/
@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;
    private boolean hasPreviousPage;//前一页
    private boolean hasNextPage;//下一页
    private boolean isFirstPage;//第一页
    private boolean isLastPage;//最后一页
    private Integer totalPages;//总页数
    private Integer currentPage;//当前页
    private List<Integer> pages = new ArrayList<>();//页列表

    public List<Integer> getPagesList(){
        pages.add(this.currentPage);
        for (int i = 1; i <=2 ; i++) {
            if(this.currentPage-i>0){
                pages.add(0, this.currentPage-i);
            }
            if(this.currentPage+i<=this.totalPages){
                pages.add(this.currentPage+i);
            }
        }
        return pages;
    }

}
