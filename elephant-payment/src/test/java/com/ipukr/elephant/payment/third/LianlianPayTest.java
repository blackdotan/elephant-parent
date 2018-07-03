package com.ipukr.elephant.payment.third;

import com.ipukr.elephant.architecture.factory.Factory;
import com.ipukr.elephant.payment.Pay;
import com.ipukr.elephant.payment.domain.lianlian.LianlianPayOrder;
import com.ipukr.elephant.payment.domain.lianlian.LianlianWithdrawOrder;
import com.ipukr.elephant.utils.DateUtils;
import com.ipukr.elephant.utils.IdGen;
import org.junit.Before;
import org.junit.Test;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/6/5.
 */
public class LianlianPayTest {
    private Pay pay = null;


    @Before
    public void setUp() throws Exception {
        pay = Factory.getInstance().build("config.properties", "lianlian");
    }

    @Test
    public void withdraw() throws Exception {
        LianlianWithdrawOrder iLianlianWithdrawOrder = LianlianWithdrawOrder.custom()
                .no(String.valueOf(IdGen.get().nextId()))
                .dtOrder(String.valueOf(DateUtils.dateToString(DateUtils.now(), "yyyyMMddHHmmss")))
                .amount(0.05F)
                .cardNo("6217001930001550277")
                .acctName("陈伟仁")
                .build();
        pay.withdraw(iLianlianWithdrawOrder);

    }

    @Test
    public void find() throws Exception {
        LianlianPayOrder order = new LianlianPayOrder();
        order.setNo("1012627496702574592");

        pay.find(order);

    }

}
