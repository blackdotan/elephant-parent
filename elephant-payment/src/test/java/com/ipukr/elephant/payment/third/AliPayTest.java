package com.ipukr.elephant.payment.third;

import com.ipukr.elephant.architecture.factory.Factory;
import com.ipukr.elephant.payment.Pay;
import com.ipukr.elephant.payment.domain.Good;
import com.ipukr.elephant.payment.domain.PayOrder;
import com.ipukr.elephant.payment.domain.SimplePayOrder;
import com.ipukr.elephant.utils.JsonUtils;
import com.ipukr.elephant.utils.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/27.
 */
public class AliPayTest {
    private Pay pay = null;
    @Before
    public void setUp() throws Exception {
        pay = Factory.getInstance().build("config.properties", "alipay");
    }

    @Test
    public void create() throws Exception {
        String token = StringUtils.uuid();
        String no = StringUtils.uuid();
        List<Good> goods = new ArrayList<Good>();
        Good good = new Good.Builder()
                .no("20170912312312123")
                .name("Member Fee")
                .price(599F)
                .quantity(1)
                .build();
        PayOrder order = new SimplePayOrder.Builder()
                .amount(599F)
                .no(no)
                .subject("云扬健身-会员充值")
                .secret(token)
                .build();
        PayOrder pOrder = pay.create(order);
        System.out.println(pOrder.getReponse());
    }

    @Test
    public void refund() throws Exception {
        PayOrder order = new SimplePayOrder.Builder()
                .amount(0.01F)
                .no("956720210310594560")
                .subject("测试退款")
                .notifyUrl("https://tst.ipukr.cn/kong-appint/account/bill/notify")
                .build();
        PayOrder pOrder = pay.refund(order);
        System.out.println(pOrder.getReponse());
    }

    @Test
    public void verify() throws Exception {
        String text = "{\"gmt_create\":\"2018-03-11 09:56:28\",\"charset\":\"UTF-8\",\"seller_email\":\"dev@sharegyms.cn\",\"subject\":\"云扬健身-会员充值\",\"buyer_id\":\"2088312712405184\",\"invoice_amount\":\"0.60\",\"notify_id\":\"ca788693f0517e55424891c2e5b75dahe5\",\"fund_bill_list\":\"[{\\\"amount\\\":\\\"0.60\\\",\\\"fundChannel\\\":\\\"ALIPAYACCOUNT\\\"}]\",\"notify_type\":\"trade_status_sync\",\"trade_status\":\"TRADE_SUCCESS\",\"receipt_amount\":\"0.60\",\"app_id\":\"2018020602149969\",\"buyer_pay_amount\":\"0.60\",\"seller_id\":\"2088921978937201\",\"gmt_payment\":\"2018-03-11 09:56:29\",\"notify_time\":\"2018-03-11 09:56:29\",\"version\":\"1.0\",\"out_trade_no\":\"7482a260-4caf-4c6a-87a3-0b4d6a6e5ce6\",\"total_amount\":\"0.60\",\"trade_no\":\"2018031121001004180508400241\",\"auth_app_id\":\"2018020602149969\",\"buyer_logon_id\":\"wmw***@gmail.com\",\"point_amount\":\"0.00\"}";
        Map params = JsonUtils.parserString2Obj(text, Map.class);
        Assert.assertEquals(true, pay.verify(params));
    }


}
