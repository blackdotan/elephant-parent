package com.blackdotan.elephant.pay;

import com.blackdotan.elephant.pay.domain.PayCreateRequest;
import com.blackdotan.elephant.pay.domain.PayRefundRequest;
import com.blackdotan.elephant.pay.domain.PayVerifyRequest;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * @author black an
 * @date 2024/10/4
 */
public abstract class PayStrategy {

    /**
     * 创建
     *
     * @param request
     * @return
     */
    public abstract Object create(PayCreateRequest request) throws Throwable;


    /**
     * 验签
     *
     * @param request
     * @return
     */
    public abstract boolean verify(PayVerifyRequest request) throws Throwable;

    public abstract Object parser(PayVerifyRequest request) throws Throwable;


    /**
     * 退款
     *
     * @param request
     * @return
     */
    public abstract Object refund(PayRefundRequest request) throws Throwable;
}
