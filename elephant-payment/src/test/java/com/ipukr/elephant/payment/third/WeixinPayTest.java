package com.ipukr.elephant.payment.third;

import com.ipukr.elephant.architecture.factory.Factory;
import com.ipukr.elephant.payment.Pay;
import com.ipukr.elephant.payment.domain.Good;
import com.ipukr.elephant.payment.domain.PayOrder;
import com.ipukr.elephant.payment.domain.SimplePayOrder;
import com.ipukr.elephant.utils.IdGen;
import com.ipukr.elephant.utils.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
                .price(0.1F)
                .quantity(1)
                .build();
        PayOrder order = new SimplePayOrder.Builder()
                .amount(0.01F)
                .no(no)
                .remark("厦门总店")
                .subject("云扬健身-会员充值")
                .secret(no)
                .build();
        PayOrder pOrder = pay.create(order);
        System.out.println(pOrder.toString());
    }
}
