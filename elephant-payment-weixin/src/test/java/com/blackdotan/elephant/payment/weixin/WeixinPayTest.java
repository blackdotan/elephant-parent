package com.blackdotan.elephant.payment.weixin;

import com.blackdotan.elephant.payment.weixin.config.WeixinPayConfig;
import com.blackdotan.elephant.payment.weixin.domain.WeixinCreateOrder;
import com.blackdotan.elephant.payment.weixin.domain.WeixinRefundOrder;
import com.blackdotan.elephant.utils.JsonUtils;
import com.github.wxpay.sdk.WXPayUtil;
import com.blackdotan.elephant.payment.domain.RefundOrder;
import com.blackdotan.elephant.utils.DateUtils;
import com.blackdotan.elephant.utils.IdGen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.*;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/8.
 */
public class WeixinPayTest {

    private WeixinPay pay;

    @Before
    public void setUp() throws Exception {
        WeixinPayConfig config = WeixinPayConfig.custom()
                .appid("wx12c736b1c7124d61")
                .mchid("1518547931")
                .signature("ABCABCABCABCABCABCABCABCABCABCDD")
                .notify("https://cloud.ipukr.cn/kong/wxint/account/bill/callback")
                .certification("/Users/black.an/Workspace/Java/elephant/elephant-parent/elephant-payment-weixin/src/test/resources/weixin/15418283701854570.p12")
                .magnification(1.0F)
                .build();
        pay = new WeixinPay(config);
    }

    /**
     * 创建预支付订单
     * @throws Exception
     */
    @Test
    public void create() throws Exception {
        String no = String.valueOf(IdGen.get().nextId());
        // 统一支付
        WeixinCreateOrder wco = new WeixinCreateOrder.Builder()
                .no(no)
                .amount(0.1F)
                .body("购买")
                .tradeType("JSAPI")
                .openid("obwP-45MmD4OjreWVXYCP1Tj0h7w")
                .build();
        WeixinCreateOrder pOrder = pay.create(wco);
        System.out.println(pOrder.toString());

        // 小程序返回参数 & App返回参数 处理
        Date date = DateUtils.now();
        Map map = pOrder.getResmap();

        String appid = map.get("appid").toString();
        String partnerid = map.get("mch_id").toString();
        String prepayid = map.get("prepay_id").toString();
        String nonceStr = map.get("nonce_str").toString();

        Map result = new HashMap();
        result.put("appId", map.get("mch_id"));
        result.put("timeStamp", String.valueOf(date.getTime()));
        result.put("nonce_str", map.get("nonce_str"));
        result.put("package", "prepay_id=" + prepayid);
        result.put("signType", "MD5");
        result.put("sign", pay.tosignature(result));

        System.out.println(JsonUtils.parserObj2String(result));
    }

    /**
     * 测试退款功能
     * @throws Exception
     */
    @Test
    public void refund() throws Exception {

        Float amount = 2.0F;
        String ordno = "975005819370209280";
        WeixinRefundOrder order = new WeixinRefundOrder.Builder()
                .amount(amount)
                .no(ordno)
                .build();
        RefundOrder ret = pay.refund(order);
        System.out.println(ret.toString());
    }

    /**
     * 验证微信支付回调请求
     * @throws Exception
     */
    @Test
    public void verify() throws Exception {
        // 微信返回参数示例
        String text =
            "<xml><appid><![CDATA[wx7c044c92ffbee262]]></appid>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<cash_fee><![CDATA[2]]></cash_fee>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<is_subscribe><![CDATA[N]]></is_subscribe>\n" +
                "<mch_id><![CDATA[1497977952]]></mch_id>\n" +
                "<nonce_str><![CDATA[8e40e59f78c8432c8f90165379996ac9]]></nonce_str>\n" +
                "<openid><![CDATA[ozkVA0dJ4f5tw03s_uYROBKC6-eE]]></openid>\n" +
                "<out_trade_no><![CDATA[968417425890476032]]></out_trade_no>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<sign><![CDATA[C10E5CF56986DF97D0D1B05803DECDDE]]></sign>\n" +
                "<time_end><![CDATA[20180227174024]]></time_end>\n" +
                "<total_fee>2</total_fee>\n" +
                "<trade_type><![CDATA[APP]]></trade_type>\n" +
                "<transaction_id><![CDATA[4200000056201802279403991918]]></transaction_id>\n" +
            "</xml>";
        Map params = WXPayUtil.xmlToMap(text);
        Boolean bool = pay.verify(params);
        Assert.assertEquals(true, bool);
    }
}
