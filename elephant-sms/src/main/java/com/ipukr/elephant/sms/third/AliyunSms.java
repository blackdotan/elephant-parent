package com.ipukr.elephant.sms.third;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ipukr.elephant.sms.Sms;
import com.ipukr.elephant.sms.SmsResponse;
import com.ipukr.elephant.sms.config.AliyunSmsConfig;
import com.ipukr.elephant.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
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

    private void init() throws ClientException {
        logger.debug("初始化组件 {}, config={}", AliyunSms.class, config.toString());
        //
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", config.getAccessKeyId(), config.getAccessKeySecret());
        //
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", config.getProduct(), config.getDomain());
        //
        acsClient = new DefaultAcsClient(profile);
    }


    @Override
    public SmsResponse send(String mobile, String code) {
        return send(config.getTemplateId(), mobile, code);
    }

    @Override
    public SmsResponse send(String templateId, String mobile, String code) {
        Map map = new HashMap(){
            {
                put("code", code);
            }
        };
        return send(templateId, mobile, map);
    }

    @Override
    public SmsResponse send(String templateId, String mobile, String code) {
        return send(templateId, mobile, code);
    }

    @Override
    public SmsResponse send(String templateId, String mobile, Map map) {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(config.getSign());
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateId);
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(JsonUtils.parserObj2String(map));

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")){
            logger.info("发送短信成功，返回请求RequestId:{}，返回回执BizId:{}", sendSmsResponse.getRequestId(), sendSmsResponse.getBizId());
            return SmsResponse.custom().status(SmsResponse.Status.Success).msg("发送短信成功").build();
        } else if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")){
            logger.warn("发送短信失败，返回请求RequestId:{}，返回Code:{}，返回Message:{}", sendSmsResponse.getRequestId(), sendSmsResponse.getCode(), sendSmsResponse.getMessage());
            return SmsResponse.custom().status(SmsResponse.Status.Fail).msg(sendSmsResponse.getMessage()).build();
        } else {
            logger.error("发送短信失败，返回请求RequestId:{}，返回Code:{}，返回Message:{}", sendSmsResponse.getRequestId(), sendSmsResponse.getCode(), sendSmsResponse.getMessage());
            return SmsResponse.custom().status(SmsResponse.Status.Fail).msg(sendSmsResponse.getMessage()).build();
        }
    }

}
