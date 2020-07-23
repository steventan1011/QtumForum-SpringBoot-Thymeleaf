package com.dcits.qtumforum.dto;

import lombok.Data;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/22 16:32
 */

@Data
public class QuestionQueryDTO {
    private String search;
    private String tag;
    private String sort;
    private Long time;
    private Integer page;
    private Integer offset;
    private Integer size;
    private Integer column2;

}
