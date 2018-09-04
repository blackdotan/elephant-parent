package com.ipukr.elephant.payment.domain.ali;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 阿里支付公共参数 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/19.
 */
public class CommonRequestParameter {
    /**
     * 支付宝分配给开发者的应用ID
     */
    @JsonProperty(value="app_id")
    private String appId;
    /**
     * 接口名称
     */
    @JsonProperty(value="method")
    private String method;
    /**
     * 仅支持JSON
     */
    @JsonProperty(value="format")
    private String format;
    /**
     * 请求使用的编码格式，如utf-8,gbk,gb2312等
     */
    @JsonProperty(value="charset")
    private String charset;
    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    @JsonProperty(value="sign_type")
    private String signType;
    /**
     * 商户请求参数的签名串，详见签名
     */
    @JsonProperty(value="sign")
    private String sign;
    /**
     * 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
     */
    @JsonProperty(value="timestamp")
    private String timestamp;
    /**
     * 调用的接口版本，固定为：1.0
     */
    @JsonProperty(value="version")
    private String version;
    /**
     * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。
     */
    @JsonProperty(value="notify_url")
    private String notifyUrl;
    /**
     * 详见应用授权概述
     */
    @JsonProperty(value="app_auth_token")
    private String appAuthToken;
    /**
     * 请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
     */
    @JsonProperty(value="biz_content")
    private String bizContent;


}
