package com.dcits.qtumforum.dto;

import lombok.Data;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/22 16:58
 */

@Data
public class ThumbDTO {
    private Long id;
    private Long targetId;
    private Integer type;
    private UserDTO user;
    private Long gmtCreate;
    private Long gmtModified;

}
