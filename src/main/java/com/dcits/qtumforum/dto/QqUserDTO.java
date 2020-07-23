package com.dcits.qtumforum.dto;

import lombok.Data;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/23 11:07
 */

@Data
public class QqUserDTO {
    private String openId;//唯一id
    private String nickname;//用户在QQ空间的昵称。
    private String figureurl_qq;//大小为40×40像素的QQ头像URL。
    private String gender;//性别。 如果获取不到则默认返回"男"
    private String province;
    private String city;
    private String year;
    private String constellation;

}
