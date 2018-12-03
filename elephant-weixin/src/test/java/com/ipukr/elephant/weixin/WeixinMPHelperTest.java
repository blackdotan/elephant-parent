package com.ipukr.elephant.weixin;

import com.ipukr.elephant.utils.DateUtils;
import com.ipukr.elephant.weixin.request.WxTemplateMessageRequest;
import com.ipukr.elephant.weixin.response.WxAccessTokenResponse;
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
}
