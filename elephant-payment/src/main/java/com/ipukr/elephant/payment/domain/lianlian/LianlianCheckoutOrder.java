package com.ipukr.elephant.payment.domain.lianlian;

import com.ipukr.elephant.payment.domain.CheckoutOrder;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/7/5.
 */
public class LianlianCheckoutOrder extends CheckoutOrder{

    private String userId;

    private String cardNo;

    private LianlianCheckoutOrder(Builder builder) {
        setUserId(builder.userId);
        setCardNo(builder.cardNo);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public final static Builder custom() {
        return new Builder();
    }


    public static final class Builder {
        private String userId;
        private String cardNo;

        private Builder() {
        }

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public Builder cardNo(String val) {
            cardNo = val;
            return this;
        }

        public LianlianCheckoutOrder build() {
            return new LianlianCheckoutOrder(this);
        }
    }
}
