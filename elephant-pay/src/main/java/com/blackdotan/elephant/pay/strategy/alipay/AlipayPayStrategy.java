package com.blackdotan.elephant.pay.strategy.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.blackdotan.elephant.pay.PayStrategy;
import com.blackdotan.elephant.pay.domain.PayCreateRequest;
import com.blackdotan.elephant.pay.domain.PayRefundRequest;
import com.blackdotan.elephant.pay.domain.PayVerifyRequest;
import com.blackdotan.elephant.utils.JsonUtils;
import com.blackdotan.elephant.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 阿里支付
 *
 * @author black an
 * @date 2024/10/4
 */
@Slf4j
public class AlipayPayStrategy extends PayStrategy {

    private static final String TRADE_TYPE = "71";
    private static final String URL = "https://openapi.alipay.com/gateway.do";

    private AlipayPayStrategyConfig config;

    public AlipayPayStrategy(AlipayPayStrategyConfig config) {
        this.config = config;
    }

    @Override
    public Object create(PayCreateRequest request) throws Throwable {
        AlipayClient alipayClient = buildAlipayClient();
        String tno = request.getTno();
        BigDecimal amount = request.getAmount();
        String remark = request.getRemark();
        String openId = request.getOpenId();
        String notifyUrl = StringUtils.isEmpty(request.getNotifyUrl()) ? config.getNotifyUrl() : request.getNotifyUrl();

        AlipayTradeCreateRequest alipayTradeCreateRequest = new AlipayTradeCreateRequest();
        Map<String, Object> bizContent = new HashMap();
        bizContent.put("out_trade_no", tno);
        bizContent.put("total_amount", amount);
        bizContent.put("subject", remark);
        bizContent.put("buyer_id", openId);
        bizContent.put("timeout_express", "10m");
        bizContent.put("product_code", TRADE_TYPE);
        alipayTradeCreateRequest.setNotifyUrl(notifyUrl);

        alipayTradeCreateRequest.setBizContent(JsonUtils.parserObj2String(bizContent));
        AlipayTradeCreateResponse response = alipayClient.execute(alipayTradeCreateRequest);
        if (response.isSuccess()) {
            return response;
        } else {
            String error = StringUtils.easyAppend("调用失败，Msg={}", response.getMsg());
            throw new RuntimeException(error);
        }
    }

    @Override
    public boolean verify(PayVerifyRequest request) throws Throwable {
        String xml = request.getSign();
        // 签名验证(对支付宝返回的数据验证，确定是支付宝返回的)
        String signType = config.getSignType();
        String charset = config.getCharset();
        String thirdPlatformPublicKeyContent = new String(config.getThirdPlatformCertContent(), StandardCharsets.UTF_8);

        //调用SDK验证签名
        Map<String, String> params = JsonUtils.parserString2Obj(xml, Map.class);
        String tradeStatus = params.get("trade_status");
        return AlipaySignature.rsaCheckV1(params, thirdPlatformPublicKeyContent, charset, signType);
    }

    @Override
    public Object parser(PayVerifyRequest request) throws Throwable {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object refund(PayRefundRequest request) throws Throwable {
        BigDecimal amount = request.getAmount();
        String transactionId = request.getTransactionId();
        String rno = request.getRno();
        String notifyUrl = StringUtils.isEmpty(request.getNotifyUrl()) ? config.getNotifyUrl() : request.getNotifyUrl();

        AlipayClient alipayClient = buildAlipayClient();
        AlipayTradeRefundRequest alipayTradeRefundRequest = new AlipayTradeRefundRequest();
        Map<String, Object> bizContent = new HashMap();
        bizContent.put("trade_no", transactionId);
        bizContent.put("refund_amount", amount);
        bizContent.put("out_request_no", rno);
        alipayTradeRefundRequest.setBizContent(bizContent.toString());
        alipayTradeRefundRequest.setNotifyUrl(notifyUrl);
        AlipayTradeRefundResponse response = alipayClient.execute(alipayTradeRefundRequest);
        if (response.isSuccess()) {
            // System.out.println("调用成功");
            return response;
        } else {
            // System.out.println("调用失败");
            String error = StringUtils.easyAppend("调用失败，Msg={}", response.getMsg());
            throw new RuntimeException(error);
        }
    }

    private AlipayClient buildAlipayClient() throws Exception {
        String appId = config.getAppId();
        String FORMAT = config.getFormat();
        String SIGN_TYPE = config.getSignType();
        String CHARSET = config.getCharset();
        String privateKeyContent = new String(config.getPrivateKeyContent(), StandardCharsets.UTF_8);
        String alipayPubKeyContent = new String(config.getThirdPlatformCertContent(), StandardCharsets.UTF_8);

        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl(URL);
        alipayConfig.setAppId(appId);
        alipayConfig.setPrivateKey(privateKeyContent);
        alipayConfig.setFormat(FORMAT);
        alipayConfig.setCharset(CHARSET);
        alipayConfig.setSignType(SIGN_TYPE);
        alipayConfig.setAlipayPublicKey(alipayPubKeyContent);
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        return alipayClient;
    }
}
