package com.ipukr.elephant.payment.alipay;


import com.ipukr.elephant.common.exception.IllegalArgumentEx;
import com.ipukr.elephant.payment.Pay;
import com.ipukr.elephant.payment.alipay.config.AliPayConfig;
import com.ipukr.elephant.payment.alipay.domain.AliCreateOrder;
import com.ipukr.elephant.payment.alipay.domain.AliQueryOrder;
import com.ipukr.elephant.payment.alipay.domain.AliRefundOrder;
import com.ipukr.elephant.payment.alipay.utils.MathUtils;
import com.ipukr.elephant.payment.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/9/6.
 */
public class AliPay implements Pay {

    private static final Logger logger = LoggerFactory.getLogger(AliPay.class);

    private AliPayConfig config;

    private AlipayClient client;

    public AliPay(AliPayConfig config) {
        this.config = config;
        this.init();
    }

    private void init() {
        logger.debug("初始化组件 {}, config={}", AliPay.class.getCanonicalName(), config.toString());
        client = new DefaultAlipayClient(config.getUrl(),
                config.getAppId(),
                config.getAppPrivateKey(),
                config.getFormat(),
                config.getCharset(),
                config.getAlipayPublicKey(),
                config.getSignType());
    }

    @Override
    public <T extends CloseOrder> boolean close(T order) {
        throw new UnsupportedOperationException("不支持的方法");
    }


    @Override
    public <T extends CreateOrder> T create(T order) throws Exception {
        if (order instanceof AliCreateOrder) {
            Float f = MathUtils.multiply(order.getAmount(), config.getMagnification(), 2);

            order.setAmount(f);

            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

            model.setSubject(((AliCreateOrder) order).getSubject());
            model.setOutTradeNo(order.getNo());
            model.setTimeoutExpress("15d");

            model.setGoodsType("0");
            model.setProductCode("QUICK_MSECURITY_PAY");

            model.setTotalAmount(Float.toString(order.getAmount()));

            if (((AliCreateOrder) order).getGoods() != null && !((AliCreateOrder) order).getGoods().isEmpty()) {
                List<GoodsDetail> details = new ArrayList<GoodsDetail>();
                StringBuffer body = new StringBuffer();
                for (AliCreateOrder.Good good : ((AliCreateOrder) order).getGoods()) {
                    body.append(good.getName()).append(",");
                }
                model.setBody(body.toString());
            }

            request.setNotifyUrl(config.getNotify());

            request.setBizModel(model);

            AlipayTradeAppPayResponse response = client.sdkExecute(request);

            if (response.isSuccess()) {
                ((AliCreateOrder) order).setResponse(response.getBody());
                ((AliCreateOrder) order).setSuccess(true);
                return order;
            } else {
                ((AliCreateOrder) order).setSuccess(false);
                return order;
            }
        } else {
            throw new IllegalArgumentEx("创建阿里支付订单失败，传入的参数不是{}", AliCreateOrder.class.getName());
        }
    }

    /**
     * @param order
     * @return
     * @throws Exception
     */
    @Override
    public <T extends QueryOrder> T find(T order) throws Exception {
        if( order instanceof AliQueryOrder) {
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            AlipayTradeQueryModel model = new AlipayTradeQueryModel();
            model.setOutTradeNo(order.getNo());

            request.setBizModel(model);

            AlipayTradeQueryResponse response = client.execute(request);

            if (response.isSuccess()) {

            } else {

            }
            return order;
        } else {
            throw new IllegalArgumentEx("创建阿里支付订单失败，传入的参数不是{}", AliCreateOrder.class.getName());
        }
    }


    @Override
    public boolean transfer(TransferOrder order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean withdraw(WithdrawOrder order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends  RefundOrder> T refund(T order) throws Exception {
        if ( order instanceof AliRefundOrder) {
            Float fee = MathUtils.multiply(order.getAmount(), config.getMagnification(), 2);

            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            AlipayTradeRefundModel model = new AlipayTradeRefundModel();
            model.setOutTradeNo(order.getNo());
            model.setRefundAmount(String.valueOf(fee));

            model.setRefundReason(((AliRefundOrder) order).getReason());

            request.setBizModel(model);
            AlipayTradeRefundResponse response = client.execute(request);
            if (response.isSuccess()) {
                ((AliRefundOrder) order).setResponse(response.getBody());
                ((AliRefundOrder) order).setSuccess(true);
            } else {
                ((AliRefundOrder) order).setSuccess(false);
            }
            return order;
        } else {
            throw new IllegalArgumentEx("阿里支付退款失败，传入的参数不是{}", AliRefundOrder.class.getName());
        }
    }

    @Override
    public boolean verify(Map params) throws Exception {
        return AlipaySignature.rsaCheckV1(params, config.getAlipayPublicKey(), config.getCharset(), config.getSignType());
    }

    @Override
    public boolean verify(Map params, String key) throws Exception {
        throw new UnsupportedOperationException("不支持此方法");
    }

    @Override
    public Map signature(Map params) throws Exception {
        throw new UnsupportedOperationException("不支持签名方法");
    }

    @Override
    public String tosignature(Map params) throws Exception {
        throw new UnsupportedOperationException("不支持签名方法");
    }

    @Override
    public String tosignature(Map params, String key) throws Exception {
        throw new UnsupportedOperationException("不支持此方法");
    }

    @Override
    public boolean checkout(CheckoutOrder order) throws Exception {
        throw new UnsupportedOperationException("不支持此方法");
    }
}
