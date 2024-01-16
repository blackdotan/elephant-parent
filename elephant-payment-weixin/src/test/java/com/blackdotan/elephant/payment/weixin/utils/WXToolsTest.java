package com.blackdotan.elephant.payment.weixin.utils;

import org.junit.Test;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/10.
 */
public class WXToolsTest {
    @Test
    public void MD5() throws Exception {
        String text = "appid=wx12c736b1c7124d61&body=账户充值&fee_type=CNY&mch_id=1518547931&nonce_str=1fef6335415c4a73&notify_url=https://cloud.ipukr.cn/kong/wxint/account/bill/callback&openid=obwP-45MmD4OjreWVXYCP1Tj0h7w&out_trade_no=1061285603045277696&time_start=1541864804686&total_fee=10&trade_type=JSAPI&key=bd88550081ec46ae3fb6217c4dc304a1";
        System.out.println(WXTools.md5(text));
    }
}
