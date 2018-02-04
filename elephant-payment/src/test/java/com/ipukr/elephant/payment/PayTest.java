package com.ipukr.elephant.payment;

import com.ipukr.elephant.architecture.factory.Factory;
import com.ipukr.elephant.payment.domain.Good;
import com.ipukr.elephant.payment.domain.PayOrder;
import com.ipukr.elephant.payment.domain.SimplePayOrder;
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
public class PayTest {
    private Pay pay = null;
    @Before
    public void setUp() throws Exception {
        pay = Factory.getInstance().build("config.properties", "alipay");
    }

    @Test
    public void create() throws Exception {

        String token = StringUtils.uuid();
        System.out.println(token);
        String no = StringUtils.uuid();
        System.out.println(no);
        List<Good> goods = new ArrayList<Good>();
        Good good = new Good.Builder()
                .no("20170912312312123")
                .name("Member Fee")
                .price(0.01F)
                .quantity(1)
                .build();
        PayOrder order = new SimplePayOrder.Builder()
                .amount(0.01F)
                .no(no)
                .subject("支付测试")
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
        Map params = new HashMap();
        params.put("gmt_create","2018-01-23 20:13:24");
        params.put("charset","UTF-8");
        params.put("seller_email","3476278266@qq.com");
        params.put("subject","支付测试");
        params.put("sign","D+L7Jhn4ihHkxdiSyPqrzTt6gV8JKO0+bWhzBPjanUV2nEgN/MTIWYPdGqwlWbFY5swYpc5XzvRayNRamdue8qraiJHNL4A96icKllG0091wIf45txLw5P74jO5muEje93X0nNgGQxZ0rAZPPXPYtrp/VF6qeJFldmBVfrnRFmoI29pfRGcGw+r/6MRUuZcXOZN+ZUjg9s676k2GeobHXbAPJvcTWrV8L4ofjcv9mx/6Tv/ipHd+rV1BTChtmlBkL8HHQntjnTAOq7I3cXMB3MJMKihnEJb7CQFxBl49eRJ7/rW6/JOaKgW4S+NBfclRd3h3pJqrYIyk82rO1i5bvw==");
        params.put("buyer_id","2088312712405184");
        params.put("invoice_amount","0.01");
        params.put("notify_id","79aaaa87f9e65b6467deb861f031eeche2");
        params.put("fund_bill_list","[{\"amount\":\"0.01\",\"fundChannel\":\"ALIPAYACCOUNT\"}]");
        params.put("notify_type","trade_status_sync");
        params.put("trade_status","TRADE_SUCCESS");
        params.put("receipt_amount","0.01");
        params.put("app_id","2017121200619752");
        params.put("buyer_pay_amount","0.01");
        params.put("sign_type","RSA2");
        params.put("seller_id","2088821424040780");
        params.put("gmt_payment","2018-01-23 20:13:25");
        params.put("notify_time","2018-01-23 20:13:25");
        params.put("version","1.0");
        params.put("out_trade_no","a8b6a78e-0bbb-4440-97a1-3189e5c78873");
        params.put("total_amount","0.01");
        params.put("trade_no","2018012321001004180213195906");
        params.put("auth_app_id","2017121200619752");
        params.put("buyer_logon_id","wmw***@gmail.com");
        params.put("point_amount","0.00");


        Assert.assertEquals(true, pay.verify(params));
    }
}
