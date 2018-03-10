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
                .price(0.05F)
                .quantity(1)
                .build();
        PayOrder order = new SimplePayOrder.Builder()
                .amount(1.99F)
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
        Map params = JsonUtils.parserString2Obj("{\"gmt_create\":\"2018-03-11 01:20:37\",\"charset\":\"UTF-8\",\"seller_email\":\"dev@sharegyms.cn\",\"subject\":\"云扬健身-会员充值\",\"sign\":\"XOdlabcZrngrmM/1qBX+LGW1Yj0u4uNPWXkVg5sYJK2pvIdLgHqTuZS3g2HWJUSkzM5blrnE+Fz/22NdKWx8GUC/FwQ3h23tio+ZimLsjw2657hNKTqvbq5rMfKX5iQggqdcSIHAVDZXdnO+cMrSKg2wDST1A6vUlacuj7+Ni/08kDj9SzGqrBV0F8psz+PhpKgKPgFAgiPqMb1f3mnIPOYDk1sskHwD0V5cEsUH8pDQSbshXbvPY3OThwXTfMgTN8etAGozj7auZp0TBKNoCTb/NwtmEENlXwOxPY+5YOcM8FNJ7M66CMLGpMZlrCiU3ye7r3u33o4x1/mtN8+rHA==\",\"buyer_id\":\"2088312712405184\",\"invoice_amount\":\"1.99\",\"notify_id\":\"08663b3c25eedca7627ed12ec785e60he5\",\"fund_bill_list\":\"[{\\\"amount\\\":\\\"1.99\\\",\\\"fundChannel\\\":\\\"ALIPAYACCOUNT\\\"}]\",\"notify_type\":\"trade_status_sync\",\"trade_status\":\"TRADE_SUCCESS\",\"receipt_amount\":\"1.99\",\"app_id\":\"2018020602149969\",\"buyer_pay_amount\":\"1.99\",\"sign_type\":\"RSA2\",\"seller_id\":\"2088921978937201\",\"gmt_payment\":\"2018-03-11 01:20:42\",\"notify_time\":\"2018-03-11 01:20:42\",\"version\":\"1.0\",\"out_trade_no\":\"7bddc580-3eee-4068-acea-41568ae2cc88\",\"total_amount\":\"1.99\",\"trade_no\":\"2018031121001004180508379973\",\"auth_app_id\":\"2018020602149969\",\"buyer_logon_id\":\"wmw***@gmail.com\",\"point_amount\":\"0.00\"}", Map.class);
        Assert.assertEquals(true, pay.verify(params));
    }


}
