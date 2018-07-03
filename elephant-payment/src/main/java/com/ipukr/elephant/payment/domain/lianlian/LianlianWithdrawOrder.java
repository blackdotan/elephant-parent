package com.ipukr.elephant.payment.domain.lianlian;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ipukr.elephant.payment.domain.WithdrawOrder;

/**
 * 连连支付转账 <br>
 *
 * 请求地址：：https://instantpay.lianlianpay.com/paymentapi/payment.htm
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/6/1.
 */
public class LianlianWithdrawOrder extends WithdrawOrder {


    /**
     * 商户付款时间
     */
    @JsonProperty("dt_order")
    private String dtOrder;
    /**
     * 付款金额
     */
    @JsonProperty("money_order")
    private String moneyOrder;
    /**
     * 收款银行账号
     */
    @JsonProperty("card_no")
    private String cardNo;
    /**
     * 收款人姓名
     */
    @JsonProperty("acct_name")
    private String acctName;
    /**
     * 收款银行名称
     */
    @JsonProperty("bank_name")
    private String bankName;
    /**
     * 订单描述
     */
    @JsonProperty("info_order")
    private String infoOrder;
    /**
     * 对公对私标志
     */
    @JsonProperty("flag_card")
    private String flagCard;
    /**
     * 服务器异步通知地址
     */
    @JsonProperty("notify_url")
    private String notifyUrl;
    /**
     * 商户编号
     */
    @JsonProperty("oid_partner")
    private String oidPartner;
    /**
     * 版本号
     */
    @JsonProperty("api_version")
    private String apiVersion;
    /**
     * 签名方式
     */
    @JsonProperty("sign")
    private String sign;
    /**
     * 签名
     */
    @JsonProperty("sign_type")
    private String signType;

    private Float amount;

    /**
     * 收款备注
     */
    @JsonProperty("memo")
    private String memo;

    private LianlianWithdrawOrder(Builder builder) {
        setNo(builder.no);
        setDtOrder(builder.dtOrder);
        setMoneyOrder(builder.moneyOrder);
        setCardNo(builder.cardNo);
        setAcctName(builder.acctName);
        setBankName(builder.bankName);
        setInfoOrder(builder.infoOrder);
        setFlagCard(builder.flagCard);
        setNotifyUrl(builder.notifyUrl);
        setOidPartner(builder.oidPartner);
        setApiVersion(builder.apiVersion);
        setSign(builder.sign);
        setSignType(builder.signType);
        setAmount(builder.amount);
        setMemo(builder.memo);
    }

    public static Builder custom() {
        return new Builder();
    }

    public String getDtOrder() {
        return dtOrder;
    }

    public void setDtOrder(String dtOrder) {
        this.dtOrder = dtOrder;
    }

    public String getMoneyOrder() {
        return moneyOrder;
    }

    public void setMoneyOrder(String moneyOrder) {
        this.moneyOrder = moneyOrder;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getInfoOrder() {
        return infoOrder;
    }

    public void setInfoOrder(String infoOrder) {
        this.infoOrder = infoOrder;
    }

    public String getFlagCard() {
        return flagCard;
    }

    public void setFlagCard(String flagCard) {
        this.flagCard = flagCard;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getOidPartner() {
        return oidPartner;
    }

    public void setOidPartner(String oidPartner) {
        this.oidPartner = oidPartner;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public static final class Builder {
        private String no;
        private String dtOrder;
        private String moneyOrder;
        private String cardNo;
        private String acctName;
        private String bankName;
        private String infoOrder;
        private String flagCard;
        private String notifyUrl;
        private String oidPartner;
        private String apiVersion;
        private String sign;
        private String signType;
        private Float amount;
        private String memo;

        private Builder() {
        }

        public Builder no(String val) {
            no = val;
            return this;
        }

        public Builder dtOrder(String val) {
            dtOrder = val;
            return this;
        }

        public Builder moneyOrder(String val) {
            moneyOrder = val;
            return this;
        }

        public Builder cardNo(String val) {
            cardNo = val;
            return this;
        }

        public Builder acctName(String val) {
            acctName = val;
            return this;
        }

        public Builder bankName(String val) {
            bankName = val;
            return this;
        }

        public Builder infoOrder(String val) {
            infoOrder = val;
            return this;
        }

        public Builder flagCard(String val) {
            flagCard = val;
            return this;
        }

        public Builder notifyUrl(String val) {
            notifyUrl = val;
            return this;
        }

        public Builder oidPartner(String val) {
            oidPartner = val;
            return this;
        }

        public Builder apiVersion(String val) {
            apiVersion = val;
            return this;
        }

        public Builder sign(String val) {
            sign = val;
            return this;
        }

        public Builder signType(String val) {
            signType = val;
            return this;
        }

        public Builder amount(Float val) {
            amount = val;
            return this;
        }

        public Builder memo(String val) {
            memo = val;
            return this;
        }

        public LianlianWithdrawOrder build() {
            return new LianlianWithdrawOrder(this);
        }
    }
}
