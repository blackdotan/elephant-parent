package com.blackdotan.elephant.weixin.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/10/31.
 */
public class WxCode2SessionResponse {
    /**
     * 用户唯一标识
     * */
    private String openid;
    /**
     * 会话密钥
     * */
    @JsonProperty(value = "session_key")
    private String sessionKey;
    /**
     * 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
     * */
    private String unionid;
    /**
     * 错误码
     * -1：系统繁忙，此时请开发者稍候再试
     * 0：请求成功
     * 40029：code 无效
     * 45011：频率限制，每个用户每分钟100次
     * */
    private Integer errcode;
    /**
     * 错误信息
     * */
    private String errMsg;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "WxCode2SessionResponse{" +
                "openid='" + openid + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", unionid='" + unionid + '\'' +
                ", errcode=" + errcode +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
