package com.blackdotan.elephant.payment.strategy;


import com.blackdotan.elephant.payment.strategy.domain.PayCreateRequest;
import com.blackdotan.elephant.payment.strategy.domain.PayRefundRequest;

import java.io.Serializable;

/**
 *
 */
public interface PayStrategy {
    /**
     * @param request
     * @return
     */
    Serializable create(PayCreateRequest request) throws Throwable;

    /**
     * @param request
     * @return
     * @throws Exception
     */
    Serializable refund(PayRefundRequest request) throws Throwable;
}
