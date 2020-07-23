package com.dcits.qtumforum.dto;

import lombok.Data;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/22 14:36
 */

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String avatarUrl;
    private Integer vipRank;
    private Integer groupId;
}
