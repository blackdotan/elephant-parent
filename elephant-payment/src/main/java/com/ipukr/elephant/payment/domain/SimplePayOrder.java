package com.ipukr.elephant.payment.domain;

import java.util.List;

/**
 * 支付订单 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/27.
 */
public class SimplePayOrder extends PayOrder {


    private SimplePayOrder(Builder builder) {
        setNo(builder.no);
        setExternalNo(builder.externalNo);
        setSubject(builder.subject);
        setAmount(builder.amount);
        setRemark(builder.remark);
        setFrom(builder.from);
        setTo(builder.to);
        setGoods(builder.goods);
        setSecret(builder.secret);
        setReponse(builder.reponse);
        setNotifyUrl(builder.notifyUrl);
    }

    public final static Builder custom() {
        return new Builder();
    }


    public static final class Builder {
        private String no;
        private String externalNo;
        private String subject;
        private float amount;
        private String remark;
        private Account from;
        private Account to;
        private List<Good> goods;
        private String secret;
        private String reponse;
        private String notifyUrl;

        public Builder() {
        }

        public Builder no(String val) {
            no = val;
            return this;
        }

        public Builder externalNo(String val) {
            externalNo = val;
            return this;
        }

        public Builder subject(String val) {
            subject = val;
            return this;
        }

        public Builder amount(float val) {
            amount = val;
            return this;
        }

        public Builder remark(String val) {
            remark = val;
            return this;
        }

        public Builder from(Account val) {
            from = val;
            return this;
        }

        public Builder to(Account val) {
            to = val;
            return this;
        }

        public Builder goods(List<Good> val) {
            goods = val;
            return this;
        }

        public Builder secret(String val) {
            secret = val;
            return this;
        }

        public Builder reponse(String val) {
            reponse = val;
            return this;
        }

        public Builder notifyUrl(String val) {
            notifyUrl = val;
            return this;
        }


        public SimplePayOrder build() {
            return new SimplePayOrder(this);
        }
    }


}
