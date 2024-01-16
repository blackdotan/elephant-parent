package com.blackdotan.elephant.weixin;

import com.blackdotan.elephant.utils.DateUtils;
import com.blackdotan.elephant.weixin.bean.WxPhoneInfo;
import com.blackdotan.elephant.weixin.bean.WxUserInfo;
import com.blackdotan.elephant.weixin.request.GetWxACodeUnlimitRequest;
import com.blackdotan.elephant.weixin.request.WxTemplateMessageRequest;
import com.blackdotan.elephant.weixin.response.WxAccessTokenResponse;
import com.blackdotan.elephant.weixin.response.WxCode2SessionResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/19.
 */
public class WeixinMPHelperTest {

    private WeixinMPHelper helper;

    @Before
    public void setUp() throws Exception {
        helper = new WeixinMPHelper();
    }

    /**
     * 测试小程序发送模板消息功能
     * 参考链接：https://developers.weixin.qq.com/miniprogram/dev/api/sendTemplateMessage.html
     * @throws Exception
     */
    @Test
    public void sendTemplateMessage() throws Exception {
        String appid = "";
        String secret = "";
        WxAccessTokenResponse access = helper.getAccessToken(appid, secret);

        List<String> items = Arrays.asList("1064420215661527040", "itself find of to that", "0.01", DateUtils.dateToString(DateUtils.now(), "yyyy-MM-dd: HH:mm:ss"), "钚氪健身房小程序云");

        Map data = new HashMap<>();
        for (int i=1; i <= items.size(); i++) {
            Map value = new HashMap();
            value.put("value", items.get(i - 1));
            data.put("keyword" + i, value);
        }

        WxTemplateMessageRequest template = WxTemplateMessageRequest.custom()
                .templateId("NJylYZ6egtYk5Y9j6ezkPmVeg6ohKVVD3HnoSINj3d8")
                .formId("wx1915285033355447fb3af83a1597869495")
                .touser("obwP-45MmD4OjreWVXYCP1Tj0h7w")
                .page("pages/myorder/myorder")
                .data(data)
                .build();
        helper.sendTemplateMessage(access.getAccess(), template);
    }

    @Test
    public void getUserInfo() throws Exception {
        WeixinMPHelper weixinMPHelper = new WeixinMPHelper();
        String appid = "";
        String secret = "";
        WxAccessTokenResponse wxAccessTokenResponse = weixinMPHelper.getAccessToken(appid,secret);
        System.out.println(wxAccessTokenResponse);

        // 微信登录
        String code = "041HKA000vChzM1nOX000lUkdO3HKA0a";
        WxCode2SessionResponse wxCode2SessionResponse = weixinMPHelper.jscode2session(appid, secret, code);
        System.out.println(wxCode2SessionResponse);


        // 获取用户信息
        String encrypted = "t5edfWIC7gshWoscBzmGkj2F2AtdVjxt4YwrZeNp07hDUkWfuRFg5kt+d8MDFvPWk2jZT8DLgeW5AzuVBLVpQ7q1UniJl/8BGMqDiDQcFa6GXSoUjxQPL9JgM/71ZywZBAknDIfShPXXy87l5aLf2koGXWrFEs+M0hs9tRr6XlY2zBgCeQJuwdSA+httnfroDiRCy/losePCYLT8vyHAMV0lCnIwIVE5PitGQclowmr1eR1bRdHFpMmrcI0htljWAcUBqp/hxVH2vQpsHS2e/329Mr0cP9wgilBegf08NnylKr1Knk1zaqfzNCLzBsUrcpVjMIM0/NLsXpOPe/vrfTxhfrKsJxPs5ozZ4g7nI9nUDxB0NWZABfJE2ywKQmQoEkm2hO+UvYgZGkB3eFxAGOwHaNFqFR9IYtvHdQe+VPGseVUj7fQOy+bair2O57vBXQWDqvEZqMv/8HCNC2fUkiyK672uJQ5BFkzGy30y2B8=";
        String iv = "A+4LgVd52tLf9bJL2AGR/w==";

        WxUserInfo wxUserInfo = weixinMPHelper.getUserInfo(wxCode2SessionResponse, encrypted,iv);
        System.out.println(wxUserInfo);
    }

    /**
     * @throws Exception
     */
    @Test
    public void getPhoneNumber() throws Exception {
        WeixinMPHelper weixinMPHelper = new WeixinMPHelper();
        String appid = "";
        String secret = "";
        WxAccessTokenResponse wxAccessTokenResponse = weixinMPHelper.getAccessToken(appid,secret);
        System.out.println(wxAccessTokenResponse);

        // 微信登录
        String code = "041sMl000oOszM1HHT000oF9Vk1sMl0X";
        WxCode2SessionResponse wxCode2SessionResponse = weixinMPHelper.jscode2session(appid, secret, code);
        String sessionKey = wxCode2SessionResponse.getSessionKey();

        System.out.println(wxCode2SessionResponse);

        String encrypted = "53cMK/8ZfhikVvdEar7HuXmMvvI1C/GGv32Z+qGOS8Ww7BEYGCO4Z3VZcxbGGycjzuumMOQWd9AaLDUkjH+bvlBzF96axrtj91/588yYAwuRnJVHQztFakIt1rYv+2+s4Vew1mMQGK28+gUnl+0zjoSVKtV2434JpfZBgSL2xa1eIr4rn1kGdR2gUK0xE5EJVAQVk+oC+YCrD05b2i4Yxw==";
        String iv = "CdA4rI8PaojmTJRpogww8Q==";

        WxPhoneInfo iWxPhoneInfo = weixinMPHelper.getPhoneNumber(sessionKey, encrypted,iv);
        System.out.println(iWxPhoneInfo);
    }

    @Test
    public void testGetWxACodeUnlimit() throws Exception {
        WeixinMPHelper weixinMPHelper = new WeixinMPHelper();
        String appid = "";
        String secret = "";
        WxAccessTokenResponse wxAccessTokenResponse = weixinMPHelper.getAccessToken(appid,secret);
        System.out.println(wxAccessTokenResponse);


        String access = wxAccessTokenResponse.getAccess();

        GetWxACodeUnlimitRequest req = new GetWxACodeUnlimitRequest();
        req.setScene("5a8107807418c652");
        req.setPage("pages/tabBar/component/component");
        String rst = weixinMPHelper.getWxACodeUnlimit(access, req);
        System.out.println(rst);
    }
}
