package com.blackdotan.elephant.weixin.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/19.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxSendTemplateMessageResponse {


    /**
     * errcode : 0
     * errmsg : ok
     */

    private int errcode;
    private String errmsg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
