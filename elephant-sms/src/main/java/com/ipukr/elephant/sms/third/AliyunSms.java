package com.ipukr.elephant.sms.third;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.context.Context;
import com.ipukr.elephant.sms.Sms;
import com.ipukr.elephant.sms.SmsStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/9/6.
 */
public class AliyunSms extends AbstractAPI implements Sms{

    private Logger logger = LoggerFactory.getLogger(AliyunSms.class);

    //产品名称:云通信短信API产品,开发者无需替换
    public static final String PRODUCT = "product";
    //产品域名,开发者无需替换
    public static final String DOMAIN = "domain";
    public static final String ACCESS_KEY_ID = "accessKeyId";
    public static final String ACCESS_KEY_SECRET = "accessKeySecret";
    public static final String TEMPLATE_ID= "templateId";
    public static final String SIGN_NAME= "sign";

    private String product;
    private String domain;
    private String accessKeyId;
    private String accessKeySecret;
    private String templateId;
    private String sign;

    public AliyunSms(Context context) throws ClientException {
        super(context);
        product = context.findStringAccordingKey(PRODUCT);
        domain = context.findStringAccordingKey(DOMAIN);
        accessKeyId = context.findStringAccordingKey(ACCESS_KEY_ID);
        accessKeySecret = context.findStringAccordingKey(ACCESS_KEY_SECRET);
        templateId = context.findStringAccordingKey(TEMPLATE_ID);
        sign = context.findStringAccordingKey(SIGN_NAME);
        this.init();
    }

    private IAcsClient acsClient;

    //组装请求对象-具体描述见控制台-文档部分内容
    private SendSmsRequest request = new SendSmsRequest();

    private AliyunSms(Builder builder) {
        super(null);
        product = builder.product;
        domain = builder.domain;
        accessKeyId = builder.accessKeyId;
        accessKeySecret = builder.accessKeySecret;
        templateId = builder.templateId;
        sign = builder.sign;
    }

    private void init() throws ClientException {
        // 
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        //
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        //
        acsClient = new DefaultAcsClient(profile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(sign);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateId);
    }

    @Override
    public SmsStatus send(String mobiles, String code) {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //必填:待发送手机号
        request.setPhoneNumbers(mobiles);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")){
            logger.info("发送短信成功，返回请求RequestId:{}，返回回执BizId:{}", sendSmsResponse.getRequestId(), sendSmsResponse.getBizId());
            return SmsStatus.Success;
        } else if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")){
            logger.warn("发送短信失败，返回请求RequestId:{}，返回Code:{}，返回Message:{}", sendSmsResponse.getRequestId(), sendSmsResponse.getCode(), sendSmsResponse.getMessage());

            return SmsStatus.Fail_Cause_Out_Of_Max_Limit;
        } else {
            logger.error("发送短信失败，返回请求RequestId:{}，返回Code:{}，返回Message:{}", sendSmsResponse.getRequestId(), sendSmsResponse.getCode(), sendSmsResponse.getMessage());
            return SmsStatus.Fail;
        }
    }

    @Override
    public SmsStatus send(Integer templateId, String mobiles, String... args) {
        return null;
    }

    public final static Builder custom() {
        return new Builder();
    }

    public static final class Builder {
        private String product;
        private String domain;
        private String accessKeyId;
        private String accessKeySecret;
        private String templateId;
        private String sign;

        public Builder() {
        }

        public Builder product(String val) {
            product = val;
            return this;
        }

        public Builder domain(String val) {
            domain = val;
            return this;
        }

        public Builder accessKeyId(String val) {
            accessKeyId = val;
            return this;
        }

        public Builder accessKeySecret(String val) {
            accessKeySecret = val;
            return this;
        }

        public Builder templateId(String val) {
            templateId = val;
            return this;
        }

        public Builder sign(String val) {
            sign = val;
            return this;
        }

        public AliyunSms build() {
            return new AliyunSms(this);
        }
    }
}
