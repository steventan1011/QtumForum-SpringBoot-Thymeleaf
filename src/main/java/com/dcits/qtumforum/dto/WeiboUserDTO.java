package com.dcits.qtumforum.dto;

import lombok.Data;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/23 11:03
 */

@Data
public class WeiboUserDTO {
    private String idstr;
    private String screen_name;//用户昵称
    private String name;//友好显示名称
    private String location;//用户所在地
    private String description;//
    private String avatar_hd;//
    private String gender;//性别，m：男、f：女、n：未知
}