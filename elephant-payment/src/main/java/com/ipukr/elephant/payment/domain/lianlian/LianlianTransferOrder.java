package com.ipukr.elephant.payment.domain.lianlian;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 连连支付转账 <br>
 *
 * 请求地址：：https://instantpay.lianlianpay.com/paymentapi/payment.htm
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/6/1.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LianlianTransferOrder {


    /**
     * 商户付款流水号
     */
    @JsonProperty("no_order")
    private String no;

    /**
     * 商户付款时间
     */
    @JsonProperty("dt_order")
    private String dtOrder;
    /**
     * 付款金额
     */
    @JsonProperty("money_order")
    private String moneyOrder;
    /**
     * 收款银行账号
     */
    @JsonProperty("card_no")
    private String cardNo;
    /**
     * 收款人姓名
     */
    @JsonProperty("acct_name")
    private String acctName;
    /**
     * 收款银行名称
     */
    @JsonProperty("bank_name")
    private String bankName;
    /**
     * 订单描述
     */
    @JsonProperty("info_order")
    private String infoOrder;
    /**
     * 对公对私标志
     */
    @JsonProperty("flag_card")
    private String flagCard;
    /**
     * 服务器异步通知地址
     */
    @JsonProperty("notify_url")
    private String notifyUrl;
    /**
     * 商户编号
     */
    @JsonProperty("oid_partner")
    private String oidPartner;
    /**
     * 版本号
     */
    @JsonProperty("api_version")
    private String apiVersion;
    /**
     * 签名方式
     */
    @JsonProperty("sign")
    private String sign;
    /**
     * 签名
     */
    @JsonProperty("sign_type")
    private String signType;

}
