package com.ipukr.elephant.sms;

/**
 * Created by ryan on 上午1:15.
 */
public enum SmsStatus {

    /**
     * 短信发送成功
     */
    Success,

    /**
     * 短信发送失败
     */
    Fail,

    /**
     * 短信发送失败，超过该手机每天最大发送上限
     */
    Fail_Cause_Out_Of_Max_Limit
}
