package com.blackdotan.elephant.sms.config;

import lombok.*;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/9/4.
 */
@Data
@Builder
@ToString
public class AliyunSmsConfig {

    private String product;
    private String domain;
    private String accessKeyId;
    private String accessKeySecret;
    private String templateId;
    private String sign;

}
