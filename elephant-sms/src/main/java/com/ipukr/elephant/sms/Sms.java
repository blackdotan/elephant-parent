package com.ipukr.elephant.sms;

import java.util.Map;

/**
 * Created by wmw on 12/28/16.
 */
public interface Sms {

    /**
     * 发送短信验证码
     * @param mobile 手机号
     * @param code 验证码
     * @return 短信发送状态
     */
    public SmsResponse send(String mobile, String code);

    /**
     *
     * @param templateId
     * @param mobile
     * @param code
     * @return
     */
    public SmsResponse send(String templateId, String mobile, String code);

    /**
     * 发送短信验证码
     * @param templateId 模板ID
     * @param mobile 手机号
     * @param map  参数 key:value
     * @return 短信发送状态
     */
    public SmsResponse send(String templateId, String mobile, Map map);
}
