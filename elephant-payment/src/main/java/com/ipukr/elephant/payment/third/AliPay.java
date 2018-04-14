package com.ipukr.elephant.payment.third;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.context.Context;
import com.ipukr.elephant.payment.Pay;
import com.ipukr.elephant.payment.domain.Account;
import com.ipukr.elephant.payment.domain.Good;
import com.ipukr.elephant.payment.domain.PayOrder;
import com.ipukr.elephant.payment.utils.MathUtils;
import com.ipukr.elephant.utils.JsonUtils;
import com.ipukr.elephant.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * 阿里支付 <br>
 *
 * <p> 接口地址：https://docs.open.alipay.com/api_1/ </p>
 * <p> 错误码：https://docs.open.alipay.com/common/105806 </p>
 *
 * Created by ryan on 2017/12/19.
 *
 * @author ryan.
 */
public class AliPay extends AbstractAPI implements Pay {

    private static final Logger logger = LoggerFactory.getLogger(AliPay.class);

    /**
     * 支付宝网关（固定）
     */
    private static final String URL = "url";
    /**
     * APPID即创建应用后生成
     */
    private static final String APP_ID = "app.id";
    /**
     * 开发者应用私钥，由开发者自己生成
     */
    private static final String APP_PRIVATE_KEY = "app.private.key";
    /**
     * 开发者应用公钥
     */
    private static final String APP_PUBLIC_KEY = "app.public.key";
    /**
     * 参数返回格式，只支持json
     */
    private static final String FORMAT = "format";
    /**
     * 请求和签名使用的字符编码格式，支持GBK和UTF
     */
    private static final String CHARSET = "charset";
    /**
     * 支付宝公钥，由支付宝生成
     */
    private static final String ALIPAY_PUBLIC_KEY = "public.key";
    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    private static final String SIGN_TYPE = "sign_type";
    /**
     * 回调地址
     * */
    private static final String NOTIFY_URL = "notify.url";

    /**
     * 放大倍数
     */
    private static final String MAGNIFICATION  = "amount.magnification.unit";



    private String url;
    private String appId;
    private String appPrivateKey ;
    private String format;
    private String charset;
    private String alipayPublicKey;
    private String signType;
    private String notify;
    private Float magnification;

    private AlipayClient client;

    public AliPay(Context context) throws ParseException {
        super(context);
        this.url = context.findStringAccordingKey(URL);
        this.appId = context.findStringAccordingKey(APP_ID);
        this.appPrivateKey = context.findStringAccordingKey(APP_PRIVATE_KEY);
        this.format = context.findStringAccordingKey(FORMAT);
        this.charset = context.findStringAccordingKey(CHARSET);
        this.alipayPublicKey = context.findStringAccordingKey(ALIPAY_PUBLIC_KEY);
        this.signType = context.findStringAccordingKey(SIGN_TYPE);
        this.notify = context.findStringAccordingKey(NOTIFY_URL);
        this.magnification = context.findNumberAccordingKey(MAGNIFICATION, 1.0F).floatValue();
        this.init();
    }

    private AliPay(Builder builder) {
        super(null);
        url = builder.url;
        appId = builder.appId;
        appPrivateKey = builder.appPrivateKey;
        format = builder.format;
        charset = builder.charset;
        alipayPublicKey = builder.alipayPublicKey;
        signType = builder.signType;
        notify = builder.notify;
        magnification = builder.magnification;
        client = builder.client;
        this.init();
    }

    private void init() {
        client = new DefaultAlipayClient(url, appId, appPrivateKey, format, charset, alipayPublicKey, signType);
    }

    @Override
    public PayOrder close(PayOrder order) {
        return null;
    }

    @Override
    public PayOrder create(PayOrder order) throws Exception {
        Float f = MathUtils.multiply(order.getAmount(), magnification, 2);

        order.setAmount(f);

        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

        model.setSubject(order.getSubject());
        model.setOutTradeNo(order.getNo());
        model.setTimeoutExpress("15d");

        model.setGoodsType("0");
        model.setProductCode("QUICK_MSECURITY_PAY");

        model.setTotalAmount(Float.toString(order.getAmount()));

        if( order.getGoods()!=null && !order.getGoods().isEmpty()) {
            List<GoodsDetail> details = new ArrayList<GoodsDetail>();
            StringBuffer body = new StringBuffer();
            for (Good good : order.getGoods()) {
                body.append(good.getName()).append(",");
            }
            model.setBody(body.toString());
        }

        request.setNotifyUrl(notify);

        request.setBizModel(model);

        AlipayTradeAppPayResponse response = client.sdkExecute(request);

        if(response.isSuccess()){
            order.setSecret(response.getBody());
            order.setReponse(response.getBody());
            order.setSuccess(true);
            logger.info("支付宝，订单创建成功, no={}, detail={}", order.getNo(), JsonUtils.parserObj2String(response));
            return order;
        } else {
            logger.info("支付宝，订单创建失败, no={}, detail={}", order.getNo(), JsonUtils.parserObj2String(response));
            order.setSuccess(false);
            return order;
        }
    }

    /**
     * @param order
     * @return
     * @throws Exception
     */
    @Override
    public PayOrder find(PayOrder order) throws Exception {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(order.getNo());
        model.setTradeNo(order.getExternalNo());

        request.setBizModel(model);

        AlipayTradeQueryResponse response = client.execute(request);
        if(response.isSuccess()){

        } else {

        }
        return order;
    }

    @Override
    public PayOrder find() {
        return null;
    }

    @Override
    public List<PayOrder> findAccordingExample(PayOrder order) {
        return null;
    }

    @Override
    public List<PayOrder> findAccordingAccount(Account account) {
        return null;
    }

    @Override
    public PayOrder transfer(PayOrder order) {
        return null;
    }

    @Override
    public PayOrder transfer(Account from, Account to, PayOrder order) {
        return null;
    }

    @Override
    public PayOrder refund(PayOrder order) throws Exception {
        Float fee = MathUtils.multiply(order.getAmount(), magnification, 2);

        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(order.getNo());
        model.setRefundAmount(String.valueOf(fee));
        model.setTradeNo(order.getExternalNo());
        model.setRefundReason(order.getRemark());

        request.setBizModel(model);
        AlipayTradeRefundResponse response = client.execute(request);
        if(response.isSuccess()) {
            logger.info("支付宝，订单退款成功，no={}, detail={}" , order.getNo(), JsonUtils.parserObj2String(response));
            order.setReponse(response.getBody());
            order.setSuccess(true);
        } else {
            logger.info("支付宝，订单退款失败，no={}, detail={}" , order.getNo(), JsonUtils.parserObj2String(response));
            order.setSuccess(false);
        }
        return order;
    }

    @Override
    public boolean verify(Map params) throws Exception {
        return AlipaySignature.rsaCheckV1(params, alipayPublicKey, charset, signType);
    }

    @Override
    public Map signature(Map params) throws Exception {
        return params;
    }

    public final static Builder custom() {
        return new Builder();
    }

    public static final class Builder {
        private String url;
        private String appId;
        private String appPrivateKey;
        private String format;
        private String charset;
        private String alipayPublicKey;
        private String signType;
        private String notify;
        private Float magnification;
        private AlipayClient client;

        public Builder() {
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Builder appId(String val) {
            appId = val;
            return this;
        }

        public Builder appPrivateKey(String val) {
            appPrivateKey = val;
            return this;
        }

        public Builder format(String val) {
            format = val;
            return this;
        }

        public Builder charset(String val) {
            charset = val;
            return this;
        }

        public Builder alipayPublicKey(String val) {
            alipayPublicKey = val;
            return this;
        }

        public Builder signType(String val) {
            signType = val;
            return this;
        }

        public Builder notify(String val) {
            notify = val;
            return this;
        }

        public Builder magnification(Float val) {
            magnification = val;
            return this;
        }

        public Builder client(AlipayClient val) {
            client = val;
            return this;
        }

        public AliPay build() {
            return new AliPay(this);
        }
    }
}
