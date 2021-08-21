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

    public void setPaginationDTO(Integer totalCount, Integer pageNum, Integer pageSize) {
        if (totalCount % pageSize == 0) {
            totalPages = totalCount / pageSize;
        } else {
            totalPages = totalCount / pageSize + 1;
        }
        this.currentPage=pageNum;
        pages.add(currentPage);
        for (int i = 1; i <=3 ; i++) {
            if(currentPage-i>0){
                pages.add(0, currentPage-i);
            }
             if(currentPage+i<=totalPages){
                pages.add(currentPage+i);
            }
        }
        //是否展示上一页
        if (currentPage == 1) {
            hasPreviousPage = false;
        } else {
            hasPreviousPage = true;
        }

        //是否展示下一页
        if (currentPage == totalPages) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }

        //是否展示首页和尾页
        if(pages.contains(1)){
            isFirstPage=false;
        }else {
            isFirstPage=true;
        }
        if(pages.contains(totalPages)){
            isLastPage=false;
        }else {
            isLastPage=true;
        }
    }
}
