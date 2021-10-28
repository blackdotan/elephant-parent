package com.blackdotan.elephant.weixin.response;

import lombok.Data;

@Data
public class WxGrantClientCredentialResponse {

    private String accessToken;

    private Integer  expiresIn;
}
