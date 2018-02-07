package com.ipukr.elephant.payment.third;

import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.context.Context;
import com.ipukr.elephant.http.third.HttpClientPool;
import com.ipukr.elephant.payment.Pay;
import com.ipukr.elephant.payment.domain.Account;
import com.ipukr.elephant.payment.domain.PayOrder;
import com.ipukr.elephant.payment.utils.MD5Tools;
import com.ipukr.elephant.utils.IdGen;
import com.ipukr.elephant.utils.SecurityUtils;
import com.ipukr.elephant.utils.StringUtils;
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
import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 请描述类 <br>
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

    public WeixinPay(Context context) throws Exception {
        super(context);
        this.init();
    }

    private void init() throws Exception {
        this.schema = context.findStringAccordingKey(HTTP_SCHEMA);
        this.hostname = context.findStringAccordingKey(HTTP_HOSTNAME);
        this.port = context.findNumberAccordingKey(HTTP_PORT).shortValue();
        this.protocol = context.findStringAccordingKey(HTTP_PROTOCOL);
        this.timeout = context.findNumberAccordingKey(HTTP_TIMEOUT).intValue();
        this.connections = context.findNumberAccordingKey(HTTP_CONNECTIONS).intValue();

        this.appid = context.findStringAccordingKey(APPID);
        this.mchid = context.findStringAccordingKey(MCHID);
        this.notify = context.findStringAccordingKey(NOTIFY_URL);
        this.signature = context.findStringAccordingKey(SIGNATURE);

         pool = HttpClientPool.custome()
                .schema(schema)
                .hostname(hostname)
                .port(port)
                .protocol(protocol)
                .timeout(timeout)
                .connections(connections)
                .routeMax(50)
                .build();
    }

    @Override
    public PayOrder create(PayOrder order) throws Exception {
        File file = new File(this.getClass().getResource("/").getPath().concat("/weixin/weixin_create_template.xml"));
        String template = IOUtils.toString(new FileInputStream(file), "UTF-8");

        String deviceInfo = "WEB";
        String nonceStr = StringUtils.uuid().substring(0, 15);
        String tradeType = "APP";

        String fee = String.valueOf(((Number) Math.max(order.getAmount() * 100, 0)).intValue());

        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
        parameters.put("appid", appid);
        parameters.put("attach", order.getRemark());
        parameters.put("body", order.getSubject());
        parameters.put("mch_id", mchid);
        parameters.put("nonce_str", nonceStr);
        parameters.put("notify_url", notify);
        parameters.put("out_trade_no", order.getNo());
        parameters.put("total_fee", fee);
        parameters.put("trade_type", tradeType);

        String sign = signature(parameters, signature, "UTF-8");
        template = template
                .replace("${appid}", appid)
                .replace("${attach}", order.getRemark())
                .replace("${body}", order.getSubject())
                .replace("${mch_id}", mchid)
                .replace("${nonce_str}", nonceStr)
                .replace("${notify_url}", notify)
                .replace("${out_trade_no}", order.getNo())
                .replace("${total_fee}", fee)
                .replace("${trade_type}", tradeType)
                .replace("${sign}", sign);


        System.out.println(template.toString());
        URI URI = new URIBuilder()
                .setScheme("https")
                .setHost("api.mch.weixin.qq.com")
                .setPath("/pay/unifiedorder")
                .setCharset(Consts.UTF_8)
                .build();

        HttpEntity entity = new StringEntity(new String(template.getBytes(), "UTF-8"), ContentType.create("application/xml", "UTF-8"));

        HttpUriRequest request = RequestBuilder.post(URI)
                .setEntity(entity)
                .build();

        HttpResponse response = pool.getConnection().execute(request);

        String content = IOUtils.toString(response.getEntity().getContent());
        System.out.println(response.toString());
        order.setReponse(content);
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
        return false;
    }


    public static String signature(SortedMap<Object,Object> parameters, String key, String charset){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = MD5Tools.MD5Encode(sb.toString(), charset).toUpperCase();
        return sign;
    }
}
