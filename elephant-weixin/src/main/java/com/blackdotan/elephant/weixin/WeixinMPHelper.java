package com.blackdotan.elephant.weixin;

import com.blackdotan.elephant.common.exception.IllegalStateEx;
import com.blackdotan.elephant.http.config.HttpClientPoolConfig;
import com.blackdotan.elephant.http.third.HttpClientPool;
import com.blackdotan.elephant.weixin.bean.WxUserInfo;
import com.blackdotan.elephant.weixin.request.WxTemplateMessageRequest;
import com.blackdotan.elephant.utils.JsonUtils;
import com.blackdotan.elephant.weixin.response.WxAccessTokenResponse;
import com.blackdotan.elephant.weixin.response.WxCode2SessionResponse;
import com.blackdotan.elephant.weixin.response.WxSendTemplateMessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;

import java.net.URI;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/19.
 */
//@Component
@Slf4j
public class WeixinMPHelper {

    private HttpClientPool pool;

    public WeixinMPHelper() throws Exception {
        HttpClientPoolConfig config = HttpClientPoolConfig.custom()
                .schema("https")
                .connections(10)
                .build();
        pool = new HttpClientPool(config);
    }

    /**
     *
     * @param appid
     * @param secret
     * @param code
     * @return
     * @throws Exception
     */
    public WxCode2SessionResponse jscode2session(String appid, String secret, String code) throws Exception {
        HttpClient client = pool.getConnection();

        URI URI = new URIBuilder()
                .setScheme("https")
                .setHost("api.weixin.qq.com")
                .setPort(443)
                .setPath("/sns/jscode2session")
                .addParameter("appid", appid)
                .addParameter("secret",secret)
                .addParameter("js_code",code)
                .addParameter("grant_type","authorization_code")
                .setCharset(Consts.UTF_8)
                .build();

        HttpUriRequest request = RequestBuilder.get(URI)
                .setCharset(Consts.UTF_8)
                .build();

        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String text = IOUtils.toString(response.getEntity().getContent());
            WxCode2SessionResponse wc2sr = JsonUtils.parserString2Obj(text, WxCode2SessionResponse.class);
            return wc2sr;
        } else {
            throw new IllegalStateEx("微信【jscode2session】失败，HTTP请求失败 Code={}", String.valueOf(response.getStatusLine().getStatusCode()));
        }
    }

    /**
     * 获取用户信息
     * @param wc2sr
     * @param encrypted
     * @param iv
     * @return
     * @throws Exception
     */
    public WxUserInfo getUserInfo(WxCode2SessionResponse wc2sr, String encrypted, String iv) throws Exception {
        WxUserInfo iWxUserInfo = WxUtil.getUserInfo(encrypted, wc2sr.getSessionKey(), iv);
        iWxUserInfo.setWc2sr(wc2sr);
        return iWxUserInfo;
    }

    /**
     * 获取授权Token
     * @param appid
     * @param secret
     * @throws Exception
     * @return
     */
    public WxAccessTokenResponse getAccessToken(String appid, String secret) throws Exception {
        HttpClient client = pool.getConnection();

        URI URI = new URIBuilder()
                .setScheme("https")
                .setHost("api.weixin.qq.com")
                .setPort(443)
                .setPath("/cgi-bin/token")
                .addParameter("appid", appid)
                .addParameter("secret", secret)
                .addParameter("grant_type", "client_credential")
                .setCharset(Consts.UTF_8)
                .build();

        HttpUriRequest request = RequestBuilder.get(URI)
                .setCharset(Consts.UTF_8)
                .build();

        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String text = IOUtils.toString(response.getEntity().getContent());
            WxAccessTokenResponse access = JsonUtils.parserString2Obj(text, WxAccessTokenResponse.class);
            if (access.getErrcode() == 0) {
                return access;
            } else {
                throw new IllegalStateEx("微信【getAccessToken】失败，获取Access_Token失败 Code={}, Body={}",
                        String.valueOf(response.getStatusLine().getStatusCode()),
                        text);
            }
        } else {
            throw new IllegalStateEx("微信【getAccessToken】失败，HTTP请求失败 Code={}", String.valueOf(response.getStatusLine().getStatusCode()));
        }
    }


    /**
     * 发送模板消息
     * @param accessToken
     * @param template
     * @return
     * @throws Exception
     */
    public boolean sendTemplateMessage(String accessToken, WxTemplateMessageRequest template) throws Exception {
        StringEntity entity  = new StringEntity(JsonUtils.parserObj2String(template), Consts.UTF_8.toString());
        entity.setContentEncoding(Consts.UTF_8.toString());

        URI URI = new URIBuilder()
                .setScheme("https")
                .setHost("api.weixin.qq.com")
                .setPort(443)
                .setPath("/cgi-bin/message/wxopen/template/send")
                .addParameter("access_token", accessToken)
                .setCharset(Consts.UTF_8)
                .build();

        HttpUriRequest request = RequestBuilder.post(URI)
                .setCharset(Consts.UTF_8)
                .setEntity(entity)
                .build();

        HttpResponse response = pool.getConnection().execute(request);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String text = IOUtils.toString(response.getEntity().getContent());
            WxSendTemplateMessageResponse wxres = JsonUtils.parserString2Obj(text, WxSendTemplateMessageResponse.class);
            if (wxres.getErrcode() == 0) {
                return true;
            } else {
                throw new IllegalStateEx("微信【sendTemplateMessage】失败，获取Access_Token失败 Code={}, Body={}",
                        String.valueOf(response.getStatusLine().getStatusCode()),
                        text);
            }
        } else {
            throw new IllegalStateEx("微信【sendTemplateMessage】失败，HTTP请求失败 Code={}", String.valueOf(response.getStatusLine().getStatusCode()));
        }
    }
}
