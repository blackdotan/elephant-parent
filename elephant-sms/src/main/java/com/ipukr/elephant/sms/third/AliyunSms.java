package com.ipukr.elephant.sms.third;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ipukr.elephant.sms.Sms;
import com.ipukr.elephant.sms.SmsResponse;
import com.ipukr.elephant.sms.config.AliyunSmsConfig;
import com.ipukr.elephant.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/6.
 */
public class AliyunSms implements Sms {

    private Logger logger = LoggerFactory.getLogger(AliyunSms.class);

    private AliyunSmsConfig config;

    private IAcsClient acsClient;


    public AliyunSms(AliyunSmsConfig config) throws ClientException {
        this.config = config;
        this.init();
    }

    /**
     * 初始化
     * @throws ClientException
     */
    private void init() throws ClientException {
        logger.debug("初始化组件 {}, config={}", AliyunSms.class, config.toString());

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", config.getAccessKeyId(), config.getAccessKeySecret());
        acsClient = new DefaultAcsClient(profile);
    }


    /**
     * 方法弃用
     * @see AliyunSms#send(String, String, String)
     * @param mobile 手机号
     * @param code   验证码
     * @return
     */
    @Override
    @Deprecated
    public SmsResponse send(String mobile, String code) {
        return send(config.getTemplateId(), mobile, code);
    }

    /**
     * @param templateId
     * @param mobile
     * @param code
     * @return
     */
    @Override
    public SmsResponse send(String templateId, String mobile, String code) {
        Map map = new HashMap(){{
            put("code", code);
        }};
        return send(templateId, mobile, map);
    }

    @Override
    public SmsResponse send(String templateId, String mobile, Map map) {
        Long version = Calendar.getInstance().getTime().getTime() / 1000;
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "default");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", config.getSign());
        request.putQueryParameter("TemplateCode", templateId);
        request.putQueryParameter("TemplateParam", JsonUtils.parserObj2String(map));

        try {
            CommonResponse response = acsClient.getCommonResponse(request);
            System.out.println(response.getData());
            return SmsResponse.custom().status(SmsResponse.Status.Success).msg(response.getData()).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return SmsResponse.custom().status(SmsResponse.Status.Success).msg(e.getErrMsg()).build();
        } catch (ClientException e) {
            e.printStackTrace();
            return SmsResponse.custom().status(SmsResponse.Status.Success).msg(e.getErrMsg()).build();
        }
    }

    @Override
    public SmsResponse batsend(String template, List<String> mobiles, List<String> signs, List<Map<String, String>> params) {
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendBatchSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumberJson", JsonUtils.parserObj2String(mobiles));
        request.putQueryParameter("SignNameJson", JsonUtils.parserObj2String(signs));
        request.putQueryParameter("TemplateCode", template);
        request.putQueryParameter("TemplateParamJson", JsonUtils.parserObj2String(params));
        try {
            CommonResponse response = acsClient.getCommonResponse(request);
            System.out.println(response.getData());
            return SmsResponse.custom().status(SmsResponse.Status.Success).msg(response.getData()).build();
        } catch (ServerException e) {
            e.printStackTrace();
            return SmsResponse.custom().status(SmsResponse.Status.Success).msg(e.getErrMsg()).build();
        } catch (ClientException e) {
            e.printStackTrace();
            return SmsResponse.custom().status(SmsResponse.Status.Success).msg(e.getErrMsg()).build();
        }
    }
}
