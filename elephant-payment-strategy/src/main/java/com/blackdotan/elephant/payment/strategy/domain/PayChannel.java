package com.blackdotan.elephant.payment.strategy.domain;

/**
 * @author black an
 * @date 2023/8/31
 */
public enum PayChannel {
    Weixin("Weixin"),
    Alipay("Alipay");

    public String value;

    PayChannel(String value) {
        this.value = value;
    }
}
