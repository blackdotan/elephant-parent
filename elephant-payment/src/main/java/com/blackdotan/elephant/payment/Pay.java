package com.blackdotan.elephant.payment;

import com.blackdotan.elephant.payment.domain.*;
import com.blackdotan.elephant.payment.domain.*;

import java.util.Map;

/**
 * 支付接口 <br>
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
     * @throws Exception
     */
    <T extends CreateOrder> T create(T order) throws Exception;

    /**
     * 获取订单
     *
     * @param order
     * @return
     */
    <T extends QueryOrder> T find(T order) throws Exception;


    /**
     * 关闭订单
     *
     * @param order
     * @return
     */
    <T extends CloseOrder> boolean close(T order)throws Exception;

    /**
     * 转账
     * @param order
     * @return
     */
    <T extends TransferOrder> boolean transfer(T order)throws Exception;


    /**
     * 无偿退款
     *
     * @param order
     * @return
     * @throws Exception
     */
    <T extends RefundOrder> T refund(T order) throws Exception;

    /**
     * 验证回调是否合法
     *
     * @param params
     * @return
     * @throws Exception
     */
    boolean verify(Map params) throws Exception;

    /**
     * 验证回调是否合法
     * @param params 返回参数
     * @param key 签名key
     * @return
     * @throws Exception
     */
    boolean verify(Map params, String key) throws Exception;

    /**
     * 提现
     * @param order
     * @return
     */
    <T extends WithdrawOrder> boolean withdraw(T order) throws Exception;


    /**
     * 校验
     * @return
     */
    <T extends CheckoutOrder> boolean checkout(T order) throws Exception;

    /**
     * 参数签名[微信]
     * @param params 待签名参数
     * @return 带签名结果
     * @throws Exception 异常信息
     */
    Map signature(Map params) throws Exception;


    /**
     * 参数签名[微信]
     * @param params 代签名参数
     * @return 签名结果
     * @throws Exception
     */
    String tosignature(Map params) throws Exception;

    /**
     * 参数签名
     * @param params 代签名参数
     * @param key 签名key
     * @return
     * @throws Exception
     */
    String tosignature(Map params, String key) throws Exception;
}
