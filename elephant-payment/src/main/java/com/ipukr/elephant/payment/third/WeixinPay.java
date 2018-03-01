package com.ipukr.elephant.payment.third;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayUtil;
import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.context.Context;
import com.ipukr.elephant.http.third.HttpClientPool;
import com.ipukr.elephant.payment.Pay;
import com.ipukr.elephant.payment.domain.Account;
import com.ipukr.elephant.payment.domain.PayOrder;
import com.ipukr.elephant.payment.utils.MD5Tools;
import com.ipukr.elephant.utils.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
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
public class WeixinPay extends AbstractAPI implements Pay {

    private HttpClientPool pool;


    private static final String HTTP_SCHEMA      = "http.schema";
    private static final String HTTP_HOSTNAME    = "http.hostname";
    private static final String HTTP_PORT        = "http.port";
    private static final String HTTP_PROTOCOL    = "http.protocol";
    private static final String HTTP_TIMEOUT     = "http.timeout";
    private static final String HTTP_CONNECTIONS = "http.connections";
    private static final String APPID      = "appid";
    private static final String MCHID      = "mchid";
    private static final String NOTIFY_URL = "notify.url";
    private static final String SIGNATURE  = "sign.key";
    private static final String CERT_PATH  = "cert.path";


    private String schema;
    private String hostname;
    private Short port;
    private String protocol;
    private Integer timeout;
    private Integer connections;
    private String appid;
    private String mchid;
    private String notify;
    private String signature;
    private InputStream cert;
    private byte[] certBytes;

    private WXPayConfig config;
    private WXPay wxpay;

    public WeixinPay(Context context) throws Exception {
        super(context);
        this.init();
    }

    private void init() throws Exception {

        this.appid = context.findStringAccordingKey(APPID);
        this.mchid = context.findStringAccordingKey(MCHID);
        this.notify = context.findStringAccordingKey(NOTIFY_URL);
        this.signature = context.findStringAccordingKey(SIGNATURE);

        InputStream cert = WeixinPay.class.getResourceAsStream(context.findStringAccordingKey(CERT_PATH));

        config = new WXPayConfig() {

            @Override
            public String getAppID() {
                return appid;
            }

            @Override
            public String getMchID() {
                return mchid;
            }

            @Override
            public String getKey() {
                return signature;
            }

            @Override
            public InputStream getCertStream() {
                return cert;
            }

            @Override
            public int getHttpConnectTimeoutMs() {
                return 6000;
            }

            @Override
            public int getHttpReadTimeoutMs() {
                return 6000;
            }
        };


        wxpay = new WXPay(config);

    }

    @Override
    public PayOrder create(PayOrder order) throws Exception {

        String fee = String.valueOf(((Number) Math.max(order.getAmount() * 100, 0)).intValue());
        Long start = DateUtils.now().getTime();
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", order.getSubject());
        data.put("out_trade_no", order.getNo());
        data.put("fee_type", "CNY");
        data.put("total_fee", fee);
        data.put("time_start", start.toString());
        data.put("notify_url", notify);
        data.put("trade_type", "APP");  // 此处指定为扫码支付

        Map<String, String> resp = wxpay.unifiedOrder(data);
        order.setResmap(resp);
        order.setReponse(JsonUtils.parserObj2String(resp));

        return order;
    }

    @Override
    public PayOrder find(PayOrder order) throws Exception {
        return null;
    }

    @Override
    public PayOrder find() throws Exception {
        return null;
    }

    @Override
    public List<PayOrder> findAccordingExample(PayOrder order) throws Exception {
        return null;
    }

    @Override
    public List<PayOrder> findAccordingAccount(Account account) throws Exception {
        return null;
    }

    @Override
    public PayOrder close(PayOrder order) throws Exception {
        return null;
    }

    @Override
    public PayOrder transfer(PayOrder order) throws Exception {
        return null;
    }

    @Override
    public PayOrder transfer(Account from, Account to, PayOrder order) throws Exception {
        return null;
    }

    @Override
    public PayOrder refund(PayOrder order) throws Exception {
        return null;
    }

    @Override
    public boolean verify(Map params) throws Exception {
        if (wxpay.isPayResultNotifySignatureValid(params)) {
            // 签名正确
            // 进行处理。
            // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
            return true;
        } else {
            // 签名错误，如果数据里没有sign字段，也认为是签名错误
            return false;
        }
    }

    @Override
    public Map signature(Map params) throws Exception {
        params.put("sign", WXPayUtil.generateSignature(params, signature));
        return params;
    }

    public static String signature(SortedMap<Object,Object> parameters, String key, String charset){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = MD5Tools.MD5Encode(sb.toString(), charset).toUpperCase();
        return sign;
    }
}
