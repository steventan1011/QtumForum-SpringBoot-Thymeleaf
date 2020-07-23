package com.dcits.qtumforum.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/22 14:38
 */

@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();  // 前后各三页的list
    private Integer totalPage;
    private Integer totalCount;

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;

        if (totalPage != 0)
            pages.add(page);
        for (int i = 1; i <= 3; i++) {//显示前后各三页
            if (page - i > 0) {
                pages.add(0, page - i);//一直往前加
            }

            if (page + i <= totalPage) {
                pages.add(page + i);//一直往后加
            }
        }

        // 是否展示上一页
        if (page == 1) {
            showPrevious = false;  // 当前是第一页 不展示上一页
        } else {
            showPrevious = true;
        }

        // 是否展示下一页
        if (page == totalPage||totalPage==0) {
            showNext = false;      // 当前是最后一页或总页数为0 不展示下一页
        } else {
            showNext = true;
        }

        // 是否展示第一页
        if (pages.contains(1)||totalPage==0) {
            showFirstPage = false;  // 前后各三页包含第一页 或 总页数为0 不展示第一页
        } else {
            showFirstPage = true;
        }

        // 是否展示最后一页
        if (pages.contains(totalPage)||totalPage==0) {
            showEndPage = false;     // 前后各三页包含最后一页 或 总页数为0 不展示最后一页
        } else {
            showEndPage = true;
        }


    }



}
