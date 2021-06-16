package com.blackdotan.elephant.payment.domain;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/9/6.
 */
public abstract class CreateOrder {
    // 订单编号
    protected String no;
    // 支付金额
    protected Float amount;
    // 支付内容
    protected String body;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
