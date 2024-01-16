package com.blackdotan.elephant.payment.strategy.third;


import com.blackdotan.elephant.payment.strategy.PayStrategy;
import com.blackdotan.elephant.payment.strategy.domain.PayCreateRequest;
import com.blackdotan.elephant.payment.strategy.domain.PayRefundRequest;

import java.io.Serializable;

public class LakalaPayStrategy implements PayStrategy {

    @Override
    public Serializable create(PayCreateRequest request) throws Exception{
        System.out.println("拉卡拉支付 Create Request");
        return null;
    }

    @Override
    public Serializable refund(PayRefundRequest request) throws Exception {
        return null;
    }
}
