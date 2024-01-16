package com.blackdotan.elephant.payment.strategy.domain;

import lombok.Data;

@Data
public class PayRefundRequest extends PayRequest {

    private String outTradeNo;

    private String transactionId;

    private String outRefundNo;

    private String openid;

    private Float totalFee;

    private Float refundFee;

    private String refundDesc;

}
