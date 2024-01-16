package com.blackdotan.elephant.payment.strategy.third;

import com.blackdotan.elephant.payment.strategy.PayStrategy;
import com.blackdotan.elephant.payment.strategy.domain.PayChannel;
import com.blackdotan.elephant.payment.strategy.domain.PayCreateRequest;
import com.blackdotan.elephant.payment.strategy.domain.PayRefundRequest;
import com.blackdotan.elephant.payment.strategy.domain.PayRequest;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundV3Request;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class WeixinPayStrategy implements PayStrategy {

    /**
     * 创建支付订单
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Serializable create(PayCreateRequest request) throws Throwable {
        Date date = Calendar.getInstance().getTime();

        WxPayService iWxPayService = build(request);

        String timeState = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        Float amount = request.getAmount();
        Integer fee = BaseWxPayRequest.yuanToFen(amount.toString());

        String description = request.getDescription();
        String no = request.getNo();
        String openid = request.getOpenid();
        String spbillCreateIp = request.getSpbillCreateIp();
        String tradeType = request.getTradeType();

        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        orderRequest.setBody(description);
        orderRequest.setOutTradeNo(no);
        // 元转成分
        orderRequest.setTotalFee(fee);
        orderRequest.setTradeType(tradeType);
        orderRequest.setSpbillCreateIp(spbillCreateIp);
        orderRequest.setOpenid(openid);
        orderRequest.setTimeStart(timeState);
        log.debug("执行微信支付，PayCreateRequest={}", orderRequest.toString());

        return iWxPayService.createOrder(orderRequest);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Serializable refund(PayRefundRequest request) throws Throwable {
        WxPayService iWxPayService = build(request);
        Float totalFee = request.getTotalFee();
        Float refundFee = request.getRefundFee();
        String outRefundNo = request.getOutRefundNo();
        String transactionId = request.getTransactionId();
        String outTradeNo = request.getOutTradeNo();
        String notifyUrl = request.getNotifyUrl();
        String refundDesc = request.getRefundDesc();

        Integer total = BaseWxPayRequest.yuanToFen(totalFee.toString());
        Integer refund = BaseWxPayRequest.yuanToFen(refundFee.toString());

        WxPayRefundRequest wxPayRefundRequest = new WxPayRefundRequest();
        WxPayRefundV3Request.Amount amount = new WxPayRefundV3Request.Amount();
        amount.setRefund(refund);
        amount.setTotal(total);

        wxPayRefundRequest.setTotalFee(total);
        wxPayRefundRequest.setRefundFee(refund);
        wxPayRefundRequest.setOutTradeNo(outTradeNo);
        wxPayRefundRequest.setTransactionId(transactionId);
        wxPayRefundRequest.setOutRefundNo(outRefundNo);
        wxPayRefundRequest.setRefundDesc(refundDesc);

        // String notifyUrl = StringUtils.easyAppend(callbackTemplate, organizationId);
        wxPayRefundRequest.setNotifyUrl(notifyUrl);

        log.debug("执行微信退款，PayRefundRequest={}", wxPayRefundRequest.toString());
        return iWxPayService.refund(wxPayRefundRequest);
    }

    /**
     * @param request
     * @return
     */
    private WxPayService build(PayRequest request) throws Throwable {
        if (request.getChannel().equals(PayChannel.Weixin)) {
            WxPayService iWxPayService = new WxPayServiceImpl();
            String appid = request.getAppid();
            String mchid = request.getMchid();
            String mchkey = request.getMchkey();
            String keyPath = request.getKeyPath();
            String privateKeyPath = request.getPrivateKeyPath();
            String privateCertPath = request.getPrivateCertPath();

            WxPayConfig config = new WxPayConfig();
            config.setAppId(appid);
            config.setMchId(mchid);
            config.setMchKey(mchkey);
            config.setKeyPath(keyPath);
            config.setPrivateKeyPath(privateKeyPath);
            config.setPrivateCertPath(privateCertPath);
            String notifyUrl = request.getNotifyUrl();
            config.setNotifyUrl(notifyUrl);
            config.setUseSandboxEnv(false);
            iWxPayService.setConfig(config);

            return iWxPayService;
        } else {
            throw new Throwable("不支持当前渠道 channel = " + request.getChannel());
        }

    }
}
