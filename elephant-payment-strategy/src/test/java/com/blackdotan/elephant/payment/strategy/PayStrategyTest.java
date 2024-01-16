package com.blackdotan.elephant.payment.strategy;

import com.blackdotan.elephant.payment.strategy.domain.PayChannel;

/**
 * @author black an
 * @date 2023/8/31
 */
public class PayStrategyTest {

    public static void main(String[] args) {
        PayChannel channel = PayChannel.Weixin;
        System.out.println(channel);
        System.out.println(PayChannel.valueOf("Weixin"));
    }
}
