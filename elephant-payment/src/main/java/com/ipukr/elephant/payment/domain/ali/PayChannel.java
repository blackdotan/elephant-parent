package com.ipukr.elephant.payment.domain.ali;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/19.
 */
public enum PayChannel {
    balance,                             // 余额
    moneyFund,                           // 余额宝
    coupon,                              // 红包
    pcredit,                             // 花呗
    pcreditpayInstallment,               // 花呗分期
    creditCard,                          // 信用卡
    creditCardExpress,                   // 信用卡快捷
    creditCardCartoon,                   // 信用卡卡通
    credit_group,                        // 信用支付类型（包含信用卡卡通、信用卡快捷、花呗、花呗分期）
    debitCardExpress,                    // 借记卡快捷
    mcard,                               // 商户预存卡
    pcard,                               // 个人预存卡
    promotion,                           // 优惠（包含实时优惠+商户优惠）
    voucher,                             // 营销券
    point,                               // 积分
    mdiscount,                           // 商户优惠
    bankPay                              // 网银
    ;
}
