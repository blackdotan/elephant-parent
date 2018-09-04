package com.ipukr.elephant.payment.domain;

/**
 * 提现订单 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/6/5.
 */
public abstract class WithdrawOrder {

    private String no;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
