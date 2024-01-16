package com.blackdotan.elephant.payment.strategy.domain;

import lombok.Data;

@Data
public class PayCreateRequest extends PayRequest {

    private String no;

    private String description;

    private String openid;

    private Float amount;

    private String tradeType;

    private String spbillCreateIp;


}
