package com.blackdotan.elephant.payment.strategy.domain;

import lombok.Data;

@Data
public class PayRequest {

    private PayChannel channel;

    private String appid;

    private String mchid;

    private String mchkey;

    private String keyPath;

    private String privateKeyPath;

    private String privateCertPath;

    private String notifyUrl;


}
