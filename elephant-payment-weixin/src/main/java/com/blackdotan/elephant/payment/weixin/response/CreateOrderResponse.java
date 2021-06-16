package com.blackdotan.elephant.payment.weixin.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/10.
 */
public class CreateOrderResponse {

    @JsonProperty(value = "return_code")
    private String code;

    @JsonProperty(value = "return_msg")
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

