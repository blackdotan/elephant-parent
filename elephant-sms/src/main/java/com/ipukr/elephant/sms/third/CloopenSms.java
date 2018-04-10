package com.ipukr.elephant.sms.third;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.context.Context;
import com.ipukr.elephant.sms.Sms;
import com.ipukr.elephant.sms.SmsStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by wmw on 12/28/16.
 */
public class CloopenSms extends AbstractAPI implements Sms {

    private Logger logger = LoggerFactory.getLogger(CloopenSms.class);

    public static final String HOST = "host";
    public static final String PORT = "port";
    public static final String SID = "sid";
    public static final String TOKEN = "token";
    public static final String APPID = "appId";
    public static final String TEMPLATEID="templateId";
    public static final String DURATION="duration";


    private String host;
    private String port;
    private String sid;
    private String token;
    private String appId;

    private CCPRestSmsSDK restAPI;

    public CloopenSms(Context context) {
        super(context);
        host = context.findStringAccordingKey(HOST);
        port = context.findStringAccordingKey(PORT);
        sid = context.findStringAccordingKey(SID);
        token = context.findStringAccordingKey(TOKEN);
        appId = context.findStringAccordingKey(APPID);
        this.init();
    }

    private CloopenSms(Builder builder) {
        super(null);
        host = builder.host;
        port = builder.port;
        sid = builder.sid;
        token = builder.token;
        appId = builder.appId;
    }

    private void init(){
        //初始化SDK
        restAPI = new CCPRestSmsSDK();

        //******************************注释*********************************************
        //*初始化服务器地址和端口                                                       *
        //*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
        //*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
        //*******************************************************************************
        restAPI.init(host, port);

        //******************************注释*********************************************
        //*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
        //*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
        //*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
        //*******************************************************************************
        restAPI.setAccount(sid, token);


        //******************************注释*********************************************
        //*初始化应用ID                                                                 *
        //*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
        //*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
        //*******************************************************************************
        restAPI.setAppId(appId);
    }

    @Override
    public SmsStatus send(String mobiles, String code) {
        HashMap<String, Object> result = null;

        //******************************注释****************************************************************
        //*调用发送模板短信的接口发送短信                                                                  *
        //*参数顺序说明：                                                                                  *
        //*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
        //*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
        //*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
        //*第三个参数是要替换的内容数组。																														       *
        //**************************************************************************************************

        //**************************************举例说明***********************************************************************
        //*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
        //*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});																		  *
        //*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
        //*********************************************************************************************************************
        String templateId = context.findStringAccordingKey(TEMPLATEID);
        String duration = context.findStringAccordingKey(DURATION);

        result = restAPI.sendTemplateSMS(mobiles, templateId, new String[]{code, duration});

        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            logger.info("发送短信成功: 返回状态 result={}", result);
            return SmsStatus.Success;
        }else{
            //异常返回输出错误码和错误信息
            logger.error("发送短息失败: 错误码={}, statusCode={},错误信息={}", result.get("statusCode"), result.get("statusMsg"));
            if(result.get("statusCode").equals("160040"))
                return SmsStatus.Fail_Cause_Out_Of_Max_Limit;
            return SmsStatus.Fail;
        }
    }

    @Override
    public SmsStatus send(Integer templateId, String mobiles, String... args) {
        HashMap<String, Object> result = null;

        //******************************注释****************************************************************
        //*调用发送模板短信的接口发送短信
        //*参数顺序说明：
        //*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号
        //*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。
        //*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”
        //*第三个参数是要替换的内容数组。
        //**************************************************************************************************

        //**************************************举例说明***********************************************************************
        //*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为
        //*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});
        //*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入
        //*********************************************************************************************************************
        String duration = context.findStringAccordingKey(DURATION);

        result = restAPI.sendTemplateSMS(mobiles, templateId.toString(), args);

        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            logger.info("发送短信成功: 返回状态 result={}", result);
            return SmsStatus.Success;
        }else{
            //异常返回输出错误码和错误信息
            logger.error("发送短息失败: 错误码={}, statusCode={},错误信息={}", result.get("statusCode"), result.get("statusMsg"));
            if(result.get("statusCode").equals("160040"))
                return SmsStatus.Fail_Cause_Out_Of_Max_Limit;
            return SmsStatus.Fail;
        }
    }

    public final static Builder custom() {
        return new Builder();
    }

    public static final class Builder {
        private String host;
        private String port;
        private String sid;
        private String token;
        private String appId;

        public Builder() {
        }

        public Builder host(String val) {
            host = val;
            return this;
        }

        public Builder port(String val) {
            port = val;
            return this;
        }

        public Builder sid(String val) {
            sid = val;
            return this;
        }

        public Builder token(String val) {
            token = val;
            return this;
        }

        public Builder appId(String val) {
            appId = val;
            return this;
        }

        public CloopenSms build() {
            return new CloopenSms(this);
        }
    }
}
