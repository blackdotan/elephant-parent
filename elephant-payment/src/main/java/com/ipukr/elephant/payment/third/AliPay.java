package com.ipukr.elephant.payment.third;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeCreateModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.GoodsDetail;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        this.init();
    }

    private void init() throws ParseException {
        this.url = context.findStringAccordingKey(URL);
        this.appId = context.findStringAccordingKey(APP_ID);
        this.appPrivateKey = context.findStringAccordingKey(APP_PRIVATE_KEY);
        this.format = context.findStringAccordingKey(FORMAT);
        this.charset = context.findStringAccordingKey(CHARSET);
        this.alipayPublicKey = context.findStringAccordingKey(ALIPAY_PUBLIC_KEY);
        this.signType = context.findStringAccordingKey(SIGN_TYPE);
        this.notify = context.findStringAccordingKey(NOTIFY_URL);
        this.magnification = context.findNumberAccordingKey(MAGNIFICATION, 1.0F).floatValue();
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
        model.setTimeoutExpress("15d");
        model.setOutTradeNo(order.getNo());
        model.setGoodsType("0");
        model.setTotalAmount(Float.toString(order.getAmount()));
        model.setProductCode(order.getSubject());
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
            order.setReponse(response.getBody());
            String msg = StringUtils.easyAppend("支付宝订单创建成功,", JsonUtils.parserObj2String(response));
            logger.debug(msg);
            return order;
        } else {
            String error = StringUtils.easyAppend("支付宝订单创建失败, error.code={}, error.msg={}", response.getCode(), response.getMsg());
            throw new RuntimeException(error);
        }
    }

    /**
     * @param order
     * @return
     * @throws Exception
     */
    @Override
    public PayOrder find(PayOrder order) throws Exception {
        // 创建API对应的request类
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        // 设置业务参数
        request.setBizContent("{" +
                "   \"out_trade_no\":\"20150320010101001\"," +
                "   \"trade_no\":\"2014112611001004680073956707\"" +
                "  }");
        // 通过alipayClient调用API，获得对应的response类
        AlipayTradeQueryResponse response = client.execute(request);
        System.out.print(response.getBody());
        return null;
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
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setRefundAmount(String.valueOf(Math.max(order.getAmount(), 0.01F)));
        model.setOutTradeNo(order.getNo());
        model.setRefundReason(order.getRemark());
        model.setTradeNo(order.getExternalNo());
        request.setBizModel(model);
        AlipayTradeRefundResponse response = client.sdkExecute(request);
        order.setReponse(response.getBody());
        return order;
    }

    @Override
    public boolean verify(Map params) throws Exception {
        System.out.println(alipayPublicKey);
        System.out.println(charset);
        System.out.println(signType);

        return AlipaySignature.rsaCheckV1(params, alipayPublicKey, charset, signType);
    }

    @Override
    public Map signature(Map params) throws Exception {
        return params;
    }
}
