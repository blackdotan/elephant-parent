package com.ipukr.elephant.payment;

import com.alipay.api.AlipayApiException;
import com.ipukr.elephant.payment.domain.Account;
import com.ipukr.elephant.payment.domain.PayOrder;
import com.ipukr.elephant.payment.domain.TransferOrder;
import com.ipukr.elephant.payment.domain.WithdrawOrder;

import java.util.List;
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
     * 关闭订单
     *
     * @param order
     * @return
     */
    PayOrder close(PayOrder order)throws Exception;

    /**
     * 转账
     * @param order
     * @return
     */
    boolean transfer(TransferOrder order)throws Exception;


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
     * 提现
     * @param order
     * @return
     */
    boolean withdraw(WithdrawOrder order) throws Exception;



    /**
     * 参数签名[微信]
     * @param params 待签名参数
     * @return 带签名结果
     * @throws Exception 异常信息
     */
    Map signature(Map params) throws Exception;

}
