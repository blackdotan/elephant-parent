package com.ipukr.elephant.payment;

import com.alipay.api.AlipayApiException;
import com.ipukr.elephant.payment.domain.Account;
import com.ipukr.elephant.payment.domain.PayOrder;

import java.util.List;
import java.util.Map;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/19.
 */
public interface Pay {
    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    PayOrder create(PayOrder order)throws Exception;

    /**
     * 获取订单
     *
     * @param order
     * @return
     */
    PayOrder find(PayOrder order)throws Exception;

    /**
     * 获取所有订单
     *
     * @return
     */
    @Deprecated
    PayOrder find()throws Exception;

    /**
     * 根据订单示例, 获取匹配的订单
     *
     * @param order
     * @return
     */
    @Deprecated
    List<PayOrder> findAccordingExample(PayOrder order)throws Exception;

    /**
     * 根据账户信息, 获取匹配订单
     *
     * @param account
     * @return
     */
    @Deprecated
    List<PayOrder> findAccordingAccount(Account account)throws Exception;

    /**
     * 关闭订单
     *
     * @param order
     * @return
     */
    PayOrder close(PayOrder order)throws Exception;

    /**
     * 转账
     *
     * @param order
     * @return
     */
    @Deprecated
    PayOrder transfer(PayOrder order)throws Exception;

    /**
     * 转账
     *
     * @param from 转账账户
     * @param to 收款账户
     * @param order 订单
     * @return
     */
    @Deprecated
    PayOrder transfer(Account from, Account to, PayOrder order)throws Exception;


    /**
     * 无偿退款
     *
     * @param order
     * @return
     * @throws Exception
     */
    PayOrder refund(PayOrder order) throws Exception;

    /**
     * 验证回调是否合法
     *
     * @param params
     * @return
     * @throws Exception
     */
    boolean verify(Map params) throws Exception;




    /**
     * 参数签名[微信]
     * @param params 待签名参数
     * @return 带签名结果
     * @throws Exception 异常信息
     */
    Map signature(Map params) throws Exception;

}
