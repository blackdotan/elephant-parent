package com.ipukr.elephant.payment.domain.ali;

import com.ipukr.elephant.payment.domain.PayOrder;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/19.
 */
public class AliPayOrder extends PayOrder {
    private String outTradeNo;                    // 支付时传入的商户订单号，与trade_no必填一个
    private String tradeNo;                       // 支付时返回的支付宝交易号，与out_trade_no必填一个
    private String tradeStatus;                   // 订单状态
    private String outRequestNo;                  // 本次退款请求流水号，部分退款时必传
    private String refundAmount;                  // 退款金额
}
