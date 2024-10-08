package com.blackdotan.elephant.pay.domain;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author black an
 * @date 2024/10/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PayRefundRequest {
    // 用户Open_Id
    private String openId;
    // outTradeNo
    private String tno;
    // 事务Id
    private String transactionId;
    // 退款单号
    private String rno;
    // 原订单金额，单位元
    private BigDecimal total;
    // 退款金额，单位元
    private BigDecimal amount;
    // 支付备注
    private String remark;
    // 支付回调地址
    private String notifyUrl;
    // 终端IP地址
    private String spbillCreateIp;
    // 支付接口版本
    private String version;
}
