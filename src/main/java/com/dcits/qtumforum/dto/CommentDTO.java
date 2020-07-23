package com.dcits.qtumforum.dto;

import com.dcits.qtumforum.model.UserAccount;
import lombok.Data;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/23 11:26
 */

@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private String gmtModifiedStr;
    private Long likeCount = 0L;
    private Integer commentCount = 0;
    private String content;
    private UserDTO user;
    private UserAccount userAccount;
}