package com.blackdotan.elephant.payment.weixin;

import com.blackdotan.elephant.payment.domain.*;
import com.github.wxpay.sdk.WXPayConstants;
import com.blackdotan.elephant.common.exception.IllegalArgumentEx;
import com.blackdotan.elephant.common.exception.IllegalStateEx;
import com.blackdotan.elephant.http.config.HttpClientPoolConfig;
import com.blackdotan.elephant.http.third.HttpClientPool;
import com.blackdotan.elephant.payment.Pay;
import com.blackdotan.elephant.payment.domain.*;
import com.blackdotan.elephant.payment.weixin.config.WeixinPayConfig;
import com.blackdotan.elephant.payment.weixin.domain.WeixinCreateOrder;
import com.blackdotan.elephant.payment.weixin.domain.WeixinRefundOrder;
import com.blackdotan.elephant.payment.weixin.utils.WXPayUtil;
import com.blackdotan.elephant.payment.weixin.utils.WXTools;
import com.blackdotan.elephant.utils.DateUtils;
import com.blackdotan.elephant.utils.JsonUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.*;

/**
 * 微信支付 <br>
 *
 *
 * SDK详见：https://github.com/wxpay/WXPay-SDK-Java
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/19.
 */
public class WeixinPay implements Pay {

    private static final Logger logger = LoggerFactory.getLogger(WeixinPay.class);

    private WeixinPayConfig config;

    private HttpClientPool pool;

    public WeixinPay(WeixinPayConfig config) throws Exception {
        this.config = config;
        this.init();
    }

    /**
     * 初始化接口
     * @throws Exception
     */
    private void init() throws Exception {
        logger.debug("初始化组件 {}, config={}", WeixinPay.class.getCanonicalName(), config.toString());
        HttpClientPoolConfig.Builder builder = new HttpClientPoolConfig.Builder()
                .schema("https")
                .protocol("TLSv1,TLSv1.1,TLSv1.2")
                .connections(20);
        if (config.getCertification() != null && !config.getCertification().equals("")) {
            File file = ResourceUtils.getFile(config.getCertification());
            logger.debug("{}, 加载微信证书路径: {}", WeixinPay.class.getCanonicalName(), file.getAbsolutePath());
            InputStream ins = new FileInputStream(file);
            byte[] bytes = new byte[ins.available()];
            IOUtils.read(ins, bytes);
            builder
                    .cert(new ByteArrayInputStream(bytes))
                    .certtype("PKCS12")
                    .cpasswd(config.getMchid().toCharArray());
        }

        pool = new HttpClientPool(builder.build());
    }


    /**
     *
     *
     * 参考链接：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
     * @param order
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T extends CreateOrder> T create(T order) throws Exception {
        if (order instanceof WeixinCreateOrder) {
            order.setAmount(order.getAmount() * config.getMagnification());
            String fee = Integer.toString(((Number) Math.max(order.getAmount() * 100, 1)).intValue());
            Long start = DateUtils.now().getTime();
            Map<String, String> data = new HashMap<String, String>();
            data.put("appid", ((WeixinCreateOrder) order).getAppid() == null || ((WeixinCreateOrder) order).getAppid().equals("") ? config.getAppid() : ((WeixinCreateOrder) order).getAppid());
            data.put("mch_id", ((WeixinCreateOrder) order).getMchid() == null || ((WeixinCreateOrder) order).getMchid().equals("") ? config.getMchid() : ((WeixinCreateOrder) order).getMchid());
            data.put("device_info", "");
            data.put("body", order.getBody());
            data.put("out_trade_no", order.getNo());
            data.put("fee_type", "CNY");
            data.put("total_fee", fee);
            data.put("sign_type", "MD5");
            data.put("product_id", "12");
            data.put("notify_url", ((WeixinCreateOrder) order).getCallback() == null || ((WeixinCreateOrder) order).getCallback().equals("") ? config.getNotify():((WeixinCreateOrder) order).getCallback());
            data.put("nonce_str", WXTools.noncestr());
            if (((WeixinCreateOrder) order).getOpenid()!=null
                    && !((WeixinCreateOrder) order).getOpenid().equals("")) {
                data.put("openid", ((WeixinCreateOrder) order).getOpenid());
            }
            data.put("trade_type", ((WeixinCreateOrder) order).getTradeType());


            // 调用微信下单接口
            HttpClient client = pool.getConnection();

            String content = WXTools.maptoxml(data, tosignature(data, ((WeixinCreateOrder) order).getSignkey() == null || ((WeixinCreateOrder) order).getSignkey().equals("") ? config.getSignature() : ((WeixinCreateOrder) order).getSignkey()));

            StringEntity entity  = new StringEntity(content, Consts.UTF_8.toString());
            entity.setContentEncoding(Consts.UTF_8.toString());

            URI URI = new URIBuilder()
                    .setScheme("https")
                    .setHost("api.mch.weixin.qq.com")
                    .setPort(443)
                    .setPath("/pay/unifiedorder")
                    .build();

            HttpUriRequest request = RequestBuilder.post(URI)
                    .addHeader("Content-Type", "text/xml")
                    .setEntity(entity)
                    .build();


            HttpResponse response = client.execute(request);
            String result = IOUtils.toString(response.getEntity().getContent());

            Map<String, String> reps = WXTools.xmltomap(result);

            ((WeixinCreateOrder) order).setSuccess(reps.get("return_code").equals("SUCCESS"));
            ((WeixinCreateOrder) order).setMessage(reps.get("return_msg"));
            if (((WeixinCreateOrder) order).getSuccess()) {
                ((WeixinCreateOrder) order).setResmap(reps);
                ((WeixinCreateOrder) order).setResponse(JsonUtils.parserObj2String(reps));
                return order;
            } else {
                throw new IllegalStateEx("创建微信订单失败，请求微信接口异常 message={}", ((WeixinCreateOrder) order).getMessage());
            }
        } else {
            throw new IllegalArgumentEx("创建微信订单失败，传入参数类型不是{}", WeixinCreateOrder.class.getName());
        }
    }


    @Override
    public <T extends QueryOrder> T find(T order) throws Exception {
        return null;
    }

    @Override
    public <T extends CloseOrder> boolean close(T order) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends TransferOrder> boolean transfer(T order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends WithdrawOrder> boolean withdraw(T order) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param order
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T extends RefundOrder> T refund(T order) throws Exception {
        if (order instanceof WeixinRefundOrder) {
            String fee = Integer.toString(((Number) Math.max(order.getAmount() * 100, 1)).intValue());

            Long start = DateUtils.now().getTime();
            Map<String, String> data = new HashMap<String, String>();
            data.put("refund_fee", fee);
            data.put("total_fee", fee);
            data.put("out_trade_no", order.getNo());
            data.put("out_refund_no", order.getNo());

            return order;
        } else {
            throw new IllegalArgumentEx("微信退款失败，传入参数类型不是{}", WeixinRefundOrder.class.getName());
        }
    }

    /**
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public boolean verify(Map params) throws Exception {
        return verify(params, config.getSignature());
    }

    @Override
    public boolean verify(Map params, String key) throws Exception {
        if (!params.containsKey("sign") ) {
            return false;
        } else {
            String sign = (String) params.remove("sign");
            return tosignature(params, key).equals(sign);
        }
    }

    /**
     * @param params 待签名参数
     * @return
     * @throws Exception
     */
    @Override
    public Map signature(Map params) throws Exception {
        params.put("sign", WXPayUtil.generateSignature(params, config.getSignature()));
        return params;
    }

    @Override
    public String tosignature(Map params) throws Exception {
        return WXPayUtil.generateSignature(params, config.getSignature(), WXPayConstants.SignType.MD5);
    }

    @Override
    public String tosignature(Map params, String key) throws Exception {
        return WXPayUtil.generateSignature(params, key, WXPayConstants.SignType.MD5);
    }


    /**
     * @param order
     * @return
     * @throws Exception
     */
    @Override
    public boolean checkout(CheckoutOrder order) throws Exception {
        throw new UnsupportedOperationException("不支持此方法");
    }

}
