package com.ipukr.elephant.sms;

/**
 * Created by ryan on 上午1:15.
 */
public class SmsResponse {

    private Status status;

    private String msg;

    private SmsResponse(Builder builder) {
        setStatus(builder.status);
        setMsg(builder.msg);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public final static Builder custom() {
        return new Builder();
    }

    public static final class Builder {
        private Status status;
        private String msg;

        public Builder() {
        }

        public Builder status(Status val) {
            status = val;
            return this;
        }

        public Builder msg(String val) {
            msg = val;
            return this;
        }

        public SmsResponse build() {
            return new SmsResponse(this);
        }
    }

    public enum Status {
        /**
         * 短信发送成功
         */
        Success,

        /**
         * 短信发送失败
         */
        Fail
    }
}
