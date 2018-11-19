package com.ipukr.elephant.payment.alipay.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/9/6.
 */
@Component
@ConfigurationProperties(prefix = "ipukr.elephant.payment.alipay")
@Getter
@Setter
public class AliPayConfig {
    /**
     * 支付宝网关（固定）
     */
    private String url;
    /**
     * APPID即创建应用后生成
     */
    private String appId;
    /**
     * 开发者应用私钥，由开发者自己生成
     */
    private String appPrivateKey ;
    /**
     * 开发者应用公钥
     */
    private String alipayPublicKey;
    /**
     * 参数返回格式，只支持json
     */
    private String format;
    /**
     * 请求和签名使用的字符编码格式，支持GBK和UTF
     */
    private String charset;
    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    private String signType;
    /**
     * 回调地址
     * */
    private String notify;
    /**
     * 放大倍数
     */
    private Float magnification =  1.0F;


}
