package com.ipukr.elephant.payment.third;

import com.ipukr.elephant.http.third.HttpClientPool;
import com.ipukr.elephant.payment.Pay;
import com.ipukr.elephant.payment.domain.Account;
import com.ipukr.elephant.payment.domain.PayOrder;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;

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
                .hostname("dictation.nuancemobility.net")
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
        template.replace("${appid}", "");
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
                .setScheme("https")
                .setHost("dictation.nuancemobility.net")
                .setPort(443)
                .setPath("/NMDPAsrCmdServlet/dictation")
                .addParameter("appId", "HTTP_NMDPPRODUCTION_Le_Trans_Letransi_20170731035035")
                .addParameter("appKey","2f7ae4a6da8735d80ea6d18c679bbcec3df6b7067af30ad9f700558da20e96d1897a776301ac3c0f520d9bbaa958a6e64e5decc851f7a248f7f7d599066b6a8f")
                .addParameter("id","00000000000000000000000000000001")
                .setCharset(Consts.UTF_8)
                .build();

        HttpUriRequest request = RequestBuilder.post(URI)
                .setCharset(Consts.UTF_8)
                .addHeader("Accept-Language", "cmn-CHN")
                .addHeader("Accept-Topic", "WebSearch")
                .addHeader("Accept", "text/plain;charset=utf-8")
                .addHeader("Content-Type", "audio/x-wav;codec=pcm;bit=16;rate=16000")
                .setEntity(template)
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
