package com.dcits.qtumforum.dto;

import lombok.Data;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/23 10:58
 */

@Data
public class GithubUserDTO {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}