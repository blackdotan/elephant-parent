package com.ipukr.elephant.payment.weixin.domain;

import com.ipukr.elephant.payment.domain.RefundOrder;
import lombok.Getter;
import lombok.Setter;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/8.
 */
@Getter
@Setter
public class WeixinRefundOrder extends RefundOrder {
    // 支付是否成功
    private Boolean success = false;
    // 支付返回信息
    private String message;

    private WeixinRefundOrder(Builder builder) {
        setNo(builder.no);
        setAmount(builder.amount);
        setSuccess(builder.success);
        setMessage(builder.message);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public static final class Builder {
        private String no;
        private Float amount;
        private Boolean success;
        private String message;

        public Builder() {
        }

        public Builder no(String val) {
            no = val;
            return this;
        }

        public Builder amount(Float val) {
            amount = val;
            return this;
        }

        public Builder success(Boolean val) {
            success = val;
            return this;
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public WeixinRefundOrder build() {
            return new WeixinRefundOrder(this);
        }
    }
}
