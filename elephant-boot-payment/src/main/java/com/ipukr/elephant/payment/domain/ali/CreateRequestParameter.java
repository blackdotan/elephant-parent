package com.ipukr.elephant.payment.domain.ali;

import com.alipay.api.domain.ExtendParams;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/19.
 */
public class CreateRequestParameter {
    /**
     * 必选
     * 	商户网站唯一订单号
     * 	商户订单号,64个字符以内、只能包含字母、数字、下划线；需保证在商户端不重复
     * 	eg: 70501111111S001111119
     */
    @JsonProperty(value = "out_trade_no")
    private String outTradeNo;

    /**
     * 可选
     * 卖家支付宝用户ID。
     * 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
     * eg: 2088102146225135
     */
    @JsonProperty(value = "seller_id")
    private String sellerId;


    /**
     * 必选
     * 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * eg: 9.00
     */
    @JsonProperty(value="total_amount")
    private float totalAmount;

    /**
     * 可选
     * 可打折金额.
     * 参与优惠计算的金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * 如果该值未传入，但传入了【订单总金额】，【不可打折金额】则该值默认为【订单总金额】-【不可打折金额】
     */
    @JsonProperty(value="discountable_amount")
    private float discountableAmount;

    /**
     * 必选
     * 商品的标题/交易标题/订单标题/订单关键字等。
     * eg: 大乐透
     */
    @JsonProperty(value="subject")
    private String subject;

    /**
     * 可选
     * 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
     * eg: Iphone6 16G
     */
    @JsonProperty(value="body")
    private String body;
    /**
     * 特殊可选
     * 买家的支付宝唯一用户号（2088开头的16位纯数字）,和buyer_logon_id不能同时为空
     * eg: 2088102146225135
     */
    @JsonProperty(value="buyer_id")
    private String buyerId;
    /**
     * 可选
     * 订单包含的商品列表信息.Json格式.
     * 其它说明详见：“商品明细说明”
     */
    @JsonProperty(value="goods_detail")
    private List<com.alipay.api.domain.GoodsDetail> mGoodsDetails;
    /**
     * 可选
     * 商户操作员编号
     */
    @JsonProperty(value = "operator_id'")
    private String operatorId;
    /**
     * 可选
     * 商户门店编号
     */
    @JsonProperty(value = "store_id'")
    private String storeId;

    /**
     * 商户机具终端编号
     */
    @JsonProperty(value = "terminal_id")
    private String terminalId;
    /**
     * 可选
     * 业务扩展参数
     */
    @JsonProperty(value = "extend_params")
    private ExtendParams extendParams;
    /**
     * 可选
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
     * 注：若为空，则默认为15d。
     * eg: 90m
     */
    @JsonProperty(value="timeout_express")
    private String timeoutExpress;

    /**
     * 可选
     * 商户传入业务信息，具体值要和支付宝约定，应用于安全，营销等参数直传场景，格式为json格式
     * eg: {"data":"123"}
     */
    @JsonProperty(value = "business_params")
    private String businessParams;

}
