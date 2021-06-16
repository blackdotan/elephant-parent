package com.blackdotan.elephant.payment.alipay.utils;

import java.math.BigDecimal;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/3/8.
 */
public class MathUtils {

    public static Float multiply(Float a, Float b){
        return (a * b);
    }

    public static Float multiply(Float a, Float b, Integer d){
        BigDecimal bd = new BigDecimal(a);
        BigDecimal nbd = bd.multiply(new BigDecimal(b));
        return nbd.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
