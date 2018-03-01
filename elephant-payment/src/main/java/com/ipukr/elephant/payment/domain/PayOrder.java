package com.ipukr.elephant.payment.domain;

import java.util.List;
import java.util.Map;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/19.
 */
public abstract class PayOrder {
    /**
     * 订单编号
     */
    private String no;
    /**
     * 外部订单编号
     */
    private String externalNo;
    /**
     * 订单标题
     */
    private String subject;
    /**
     * 交易金额
     */
    private float amount;
    /**
     * 备注信息
     */
    private String remark;

    /**
     * 订单来源账户(订单创建账户)
     */
    private Account from;

    /**
     * 订单目标账户(订单支付账户)
     */
    private Account to;
    /**
     * 商品列表
     */
    private List<Good> goods;
    /**
     * 密钥
     * */
    private String secret;
    /**
     * 第三方请求结果
     * */
    private String reponse;
    /**
     * 第三方请求结果
     *
     * 微信返回结果兼容
     */
    private Map resmap;
    /**
     * 第三方回调地址
     * */
    private String notifyUrl;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getExternalNo() {
        return externalNo;
    }

    public void setExternalNo(String externalNo) {
        this.externalNo = externalNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public Map getResmap() {
        return resmap;
    }

    public void setResmap(Map resmap) {
        this.resmap = resmap;
    }


    @Override
    public String toString() {
        return "PayOrder{" +
                "no='" + no + '\'' +
                ", externalNo='" + externalNo + '\'' +
                ", subject='" + subject + '\'' +
                ", amount=" + amount +
                ", remark='" + remark + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", goods=" + goods +
                ", secret='" + secret + '\'' +
                ", reponse='" + reponse + '\'' +
                ", resmap=" + resmap +
                ", notifyUrl='" + notifyUrl + '\'' +
                '}';
    }
}
