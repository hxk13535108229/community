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
    private Integer totalPages;//总页数
    private Integer currentPage;//当前页
    private List<Integer> pages = new ArrayList<>();//页列表

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
