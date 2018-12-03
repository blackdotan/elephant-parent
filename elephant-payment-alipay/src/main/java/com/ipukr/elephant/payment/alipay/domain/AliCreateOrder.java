package com.ipukr.elephant.payment.alipay.domain;

import com.ipukr.elephant.payment.domain.CreateOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/8.
 */
@Getter
@Setter
public class AliCreateOrder extends CreateOrder {
    private String subject;
    // 支付是否成功
    private Boolean success = false;
    // 支付返回信息
    private String message;
    // 支付返回
    private String response;
    // 商品
    private List<Good> goods;

    @Getter
    @Setter
    public static class Good {
        // 商品名称
        private String name;

    }
}
