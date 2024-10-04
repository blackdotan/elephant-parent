package com.blackdotan.elephant.pay.strategy.wechat;

import com.blackdotan.elephant.pay.PayStrategy;
import com.blackdotan.elephant.pay.domain.PayCreateRequest;
import com.blackdotan.elephant.pay.domain.PayRefundRequest;
import com.blackdotan.elephant.pay.domain.PayVerifyRequest;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 微信支付
 *
 * @author black an
 * @date 2024/10/4
 */
public class WechatPayStrategy extends PayStrategy {

    private static final String TRADE_TYPE = "JSAPI";

    private WechatPayStrategyConfig config;

    public WechatPayStrategy(WechatPayStrategyConfig config) {
        this.config = config;
    }

    @Override
    public Object create(PayCreateRequest request) throws Throwable {
        WxPayService iWxPayService = buildWxPayService();
        // 支付订单信息
        Date date = Calendar.getInstance().getTime();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        BigDecimal amount = request.getAmount();
        String remark = request.getRemark();
        String tno = request.getTno();
        String openId = request.getOpenId();
        String spbillCreateIp = request.getSpbillCreateIp();
        Integer fee = BaseWxPayRequest.yuanToFen(amount.toString());
        String notifyUrl = StringUtils.isEmpty(request.getNotifyUrl()) ? config.getNotifyUrl() : request.getNotifyUrl();

        WxPayUnifiedOrderRequest wxpurr = new WxPayUnifiedOrderRequest();
        wxpurr.setBody(remark);
        wxpurr.setOutTradeNo(tno);
        wxpurr.setTotalFee(fee);  // 元转分
        wxpurr.setTradeType(TRADE_TYPE);
        wxpurr.setSpbillCreateIp(spbillCreateIp);
        wxpurr.setOpenid(openId);
        wxpurr.setTimeStart(timestamp);
        wxpurr.setNotifyUrl(notifyUrl);
        return iWxPayService.createOrder(wxpurr);
    }

    @Override
    public boolean verify(PayVerifyRequest request) throws Throwable {
        String xml = request.getSign();
        // 微信支付商户信息
        WxPayService iWxPayService = buildWxPayService();
        WxPayOrderNotifyResult result = iWxPayService.parseOrderNotifyResult(xml);
        return result.getTransactionId() != null;
    }

    @Override
    public Object parser(PayVerifyRequest request) throws Throwable {
        String xml = request.getSign();
        // 微信支付商户信息
        WxPayService iWxPayService = buildWxPayService();
        WxPayOrderNotifyResult result = iWxPayService.parseOrderNotifyResult(xml);
        return result;
    }

    @Override
    public Object refund(PayRefundRequest request) throws Throwable {
        String tno = request.getTno();
        String transactionId = request.getTransactionId();
        String rno = request.getRno();
        String description = request.getRemark();
        // 微信支付商户信息
        String notifyUrl = StringUtils.isEmpty(request.getNotifyUrl()) ? config.getNotifyUrl() : request.getNotifyUrl();
        Integer _total = BaseWxPayRequest.yuanToFen(request.getTotal().toString());
        Integer _refund = BaseWxPayRequest.yuanToFen(request.getAmount().toString());

        WxPayService iWxPayService = buildWxPayService();
        WxPayRefundRequest wxPayRefundRequest = new WxPayRefundRequest();
        wxPayRefundRequest.setTotalFee(_total);
        wxPayRefundRequest.setRefundFee(_refund);
        wxPayRefundRequest.setOutTradeNo(tno);
        wxPayRefundRequest.setTransactionId(transactionId);
        wxPayRefundRequest.setOutRefundNo(rno);
        wxPayRefundRequest.setRefundDesc(description);
        wxPayRefundRequest.setNotifyUrl(notifyUrl);
        return iWxPayService.refund(wxPayRefundRequest);
    }

    /**
     * 创建 WxPayService 对象
     *
     * @return
     */
    private WxPayService buildWxPayService() {
        WxPayService iWxPayService = new WxPayServiceImpl();
        String mchId = config.getMchId();
        String mchKey = config.getMchKey();
        String appId = config.getAppId();
        String apiV3Ke = config.getApiV3Ke();
        byte[] keyContent = config.getThirdPlatformCertContent();
        byte[] privateKeyContent = config.getPrivateKeyContent();
        byte[] privateCertContent = config.getPrivateCertContent();

        WxPayConfig config = new WxPayConfig();
        config.setAppId(appId);
        config.setMchId(mchId);
        config.setMchKey(mchKey);
        config.setApiV3Key(apiV3Ke);
        config.setKeyContent(keyContent);
        config.setPrivateKeyContent(privateKeyContent);
        config.setPrivateCertContent(privateCertContent);
        config.setUseSandboxEnv(false);
        iWxPayService.setConfig(config);
        return iWxPayService;
    }
}
