package com.ipukr.elephant.payment.third;

import com.ipukr.elephant.http.third.HttpClientPool;
import com.ipukr.elephant.payment.Pay;
import com.ipukr.elephant.payment.domain.Account;
import com.ipukr.elephant.payment.domain.PayOrder;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/19.
 */
public class WeixinPay implements Pay {

    private HttpClientPool pool;

    public WeixinPay() throws Exception {
        this.init();
    }
    private void init() throws Exception {
         pool = HttpClientPool.custome()
                .schema("https")
                .hostname("api.mch.weixin.qq.com")
                .port((short) 443)
                .protocol("TLSv1,TLSv1.1,TLSv1.2")
                .timeout(60000)
                .connections(10)
                .routeMax(50)
                .build();
    }

    @Override
    public PayOrder create(PayOrder order) throws Exception {
        File file = new File(this.getClass().getResource("/").getPath().concat("/weixin/weixin_create_template.xml"));
        String template = IOUtils.toString(new FileInputStream(file), "UTF-8");
        template.replace("${appid}", "wx7c044c92ffbee262");
        template.replace("${attach}", "");
        template.replace("${body}", "");
        template.replace("${mch_id}", "");
        template.replace("${nonce_str}", "");
        template.replace("${notify_url}", "");
        template.replace("${out_trade_no}", "");
        template.replace("${spbill_create_ip}", "");
        template.replace("${total_fee}", "");
        template.replace("${trade_type}", "");
        template.replace("${sign}", "");

        URI URI = new URIBuilder()
                .setPath("/pay/unifiedorder")
                .setCharset(Consts.UTF_8)
                .build();

        HttpEntity entity = new StringEntity(template);

        HttpUriRequest request = RequestBuilder.post(URI)
                .setEntity(entity)
                .build();

        HttpResponse response = pool.getConnection().execute(request);

        return null;
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
}
