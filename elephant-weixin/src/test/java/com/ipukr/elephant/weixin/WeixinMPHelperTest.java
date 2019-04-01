package com.ipukr.elephant.weixin;

import com.ipukr.elephant.utils.DateUtils;
import com.ipukr.elephant.weixin.bean.WxUserInfo;
import com.ipukr.elephant.weixin.request.WxTemplateMessageRequest;
import com.ipukr.elephant.weixin.response.WxAccessTokenResponse;
import com.ipukr.elephant.weixin.response.WxCode2SessionResponse;
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
        String appid = "wx12c736b1c7124d61";
        String secret = "bd88550081ec46ae3fb6217c4dc304a1";
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
        String appid = "wx179b8946f36e74b7";
        String secret = "fe29379d72b43a30749431542c74fa8f";
        WxAccessTokenResponse  wxAccessTokenResponse = weixinMPHelper.getAccessToken(appid,secret);
        System.out.println(wxAccessTokenResponse);

        String code = "081hOSw82ShXvK0k4ex82G5Rw82hOSwt";
        WxCode2SessionResponse wxCode2SessionResponse = weixinMPHelper.jscode2session(appid, secret, code);
        System.out.println(wxCode2SessionResponse);

        String encrypted = "t5edfWIC7gshWoscBzmGkj2F2AtdVjxt4YwrZeNp07hDUkWfuRFg5kt+d8MDFvPWk2jZT8DLgeW5AzuVBLVpQ7q1UniJl/8BGMqDiDQcFa6GXSoUjxQPL9JgM/71ZywZBAknDIfShPXXy87l5aLf2koGXWrFEs+M0hs9tRr6XlY2zBgCeQJuwdSA+httnfroDiRCy/losePCYLT8vyHAMV0lCnIwIVE5PitGQclowmr1eR1bRdHFpMmrcI0htljWAcUBqp/hxVH2vQpsHS2e/329Mr0cP9wgilBegf08NnylKr1Knk1zaqfzNCLzBsUrcpVjMIM0/NLsXpOPe/vrfTxhfrKsJxPs5ozZ4g7nI9nUDxB0NWZABfJE2ywKQmQoEkm2hO+UvYgZGkB3eFxAGOwHaNFqFR9IYtvHdQe+VPGseVUj7fQOy+bair2O57vBXQWDqvEZqMv/8HCNC2fUkiyK672uJQ5BFkzGy30y2B8=";
        String iv = "A+4LgVd52tLf9bJL2AGR/w==";
        WxUserInfo wxUserInfo = weixinMPHelper.getUserInfo(wxCode2SessionResponse, encrypted,iv);
        System.out.println(wxUserInfo);
    }
}
