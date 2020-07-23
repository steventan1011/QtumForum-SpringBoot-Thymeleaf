package com.dcits.qtumforum.dto;

import lombok.Data;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/23 11:06
 */

@Data
public class QqAccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String grant_type;
}
