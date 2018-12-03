package com.ipukr.elephant.payment.weixin.domain;

import com.ipukr.elephant.payment.domain.CreateOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/8.
 */
@Getter
@Setter
public class WeixinCreateOrder extends CreateOrder {
    /**
     * 应用Id
     */
    private String appid;

    /**
     * 商户
     */
    private String mchid;

    /**
     * 签名key
     */
    private String signkey;
    /**
     * 支付类型
     * App：
     * JSAPI
     * */
    private String tradeType = "App";
    /**
     * 用户标识
     * */
    private String openid;


    /**
     * 支付是否成功
     * */
    private Boolean success = false;
    /**
     * 支付返回信息
     * */
    private String message;
    /**
     *
     * */
    private Map<String, String> resmap;
    /**
     *
     * */
    private String response;


    public WeixinCreateOrder() {

    }

    private WeixinCreateOrder(Builder builder) {
        setNo(builder.no);
        setAmount(builder.amount);
        setBody(builder.body);
        setAppid(builder.appid);
        setMchid(builder.mchid);
        setSignkey(builder.signkey);
        setTradeType(builder.tradeType);
        setOpenid(builder.openid);
        setSuccess(builder.success);
        setMessage(builder.message);
        setResmap(builder.resmap);
        setResponse(builder.response);
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
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

    public Map<String, String> getResmap() {
        return resmap;
    }

    public void setResmap(Map<String, String> resmap) {
        this.resmap = resmap;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }


    public String getSignkey() {
        return signkey;
    }

    public void setSignkey(String signkey) {
        this.signkey = signkey;
    }

    public final static Builder custom() {
        return new Builder();
    }


    public static final class Builder {
        private String no;
        private Float amount;
        private String body;
        private String appid;
        private String mchid;
        private String signkey;
        private String tradeType;
        private String openid;
        private Boolean success;
        private String message;
        private Map<String, String> resmap;
        private String response;

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

        public Builder body(String val) {
            body = val;
            return this;
        }

        public Builder appid(String val) {
            appid = val;
            return this;
        }

        public Builder mchid(String val) {
            mchid = val;
            return this;
        }

        public Builder signkey(String val) {
            signkey = val;
            return this;
        }

        public Builder tradeType(String val) {
            tradeType = val;
            return this;
        }

        public Builder openid(String val) {
            openid = val;
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

        public Builder resmap(Map<String, String> val) {
            resmap = val;
            return this;
        }

        public Builder response(String val) {
            response = val;
            return this;
        }

        public WeixinCreateOrder build() {
            return new WeixinCreateOrder(this);
        }
    }
}
