package com.ipukr.elephant.payment.alipay.domain;

import com.ipukr.elephant.payment.domain.RefundOrder;
import lombok.Getter;
import lombok.Setter;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/8.
 */
@Getter
@Setter
public class AliRefundOrder extends RefundOrder{
    /**
     * 退款原因
     * */
    private String reason;
    /**
     * 支付是否成功
     * */
    private Boolean success = false;
    /**
     * 返回结果
     * */
    private String response;
}
