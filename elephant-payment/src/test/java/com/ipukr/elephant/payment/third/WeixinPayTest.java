package com.ipukr.elephant.payment.third;

import com.github.wxpay.sdk.WXPayUtil;
import com.ipukr.elephant.architecture.factory.Factory;
import com.ipukr.elephant.payment.Pay;
import com.ipukr.elephant.payment.domain.Good;
import com.ipukr.elephant.payment.domain.PayOrder;
import com.ipukr.elephant.payment.domain.SimplePayOrder;
import com.ipukr.elephant.utils.DateUtils;
import com.ipukr.elephant.utils.IdGen;
import com.ipukr.elephant.utils.JsonUtils;
import com.ipukr.elephant.utils.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/2/7.
 */
public class WeixinPayTest {

    private Pay pay = null;


    @Before
    public void setUp() throws Exception {
        pay = Factory.getInstance().build("config.properties", "weixin");
    }

    @Test
    public void create() throws Exception {
        String no = String.valueOf(IdGen.get().nextId());
        List<Good> goods = new ArrayList<Good>();
        Good good = new Good.Builder()
                .no("20170912312312123")
                .name("Member Fee")
                .price(500F)
                .quantity(1)
                .build();
        PayOrder order = new SimplePayOrder.Builder()
                .amount(500F)
                .no(no)
                .remark("厦门总店")
                .subject("云扬健身-会员充值")
                .secret(no)
                .build();
        PayOrder pOrder = pay.create(order);
        System.out.println(pOrder.toString());
        Date date = DateUtils.now();
        Map map = pOrder.getResmap();
        Map pay = new HashMap();
        pay.put("appid", map.get("appid"));
        pay.put("partnerid", map.get("mch_id"));
        pay.put("prepayid", map.get("prepay_id"));
        pay.put("noncestr", map.get("nonce_str"));
        pay.put("timestamp", String.valueOf(date.getTime()));
        pay.put("package", "Sign=WXPay");
        pay.put("sign", WXPayUtil.generateSignature(pay, "hHJhYD6iDBvRHAzHLZ7kC9GsrWpuUmB9"));
        System.out.println(JsonUtils.parserObj2String(pay));
    }

    @Test
    public void refund() throws Exception {
        PayOrder order = new SimplePayOrder.Builder()
                .amount(2.0F)
                .no("975005819370209280")
                .remark("厦门总店")
                .subject("云扬健身-押金退还")
                .build();
        pay.refund(order);

    }

    @Test
    public void verify() throws Exception {
        String text =
                "<xml><appid><![CDATA[wx7c044c92ffbee262]]></appid>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<cash_fee><![CDATA[2]]></cash_fee>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<is_subscribe><![CDATA[N]]></is_subscribe>\n" +
                "<mch_id><![CDATA[1497977952]]></mch_id>\n" +
                "<nonce_str><![CDATA[8e40e59f78c8432c8f90165379996ac9]]></nonce_str>\n" +
                "<openid><![CDATA[ozkVA0dJ4f5tw03s_uYROBKC6-eE]]></openid>\n" +
                "<out_trade_no><![CDATA[968417425890476032]]></out_trade_no>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<sign><![CDATA[C10E5CF56986DF97D0D1B05803DECDDE]]></sign>\n" +
                "<time_end><![CDATA[20180227174024]]></time_end>\n" +
                "<total_fee>2</total_fee>\n" +
                "<trade_type><![CDATA[APP]]></trade_type>\n" +
                "<transaction_id><![CDATA[4200000056201802279403991918]]></transaction_id>\n" +
                "</xml>";
        Map params = WXPayUtil.xmlToMap(text);
        Boolean bool = pay.verify(params);
        Assert.assertEquals(true, bool);
    }
}
