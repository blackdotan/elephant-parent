package com.ipukr.elephant.weixin.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/19.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxAccessTokenResponse {


    /**
     * access_token : ACCESS_TOKEN
     * expires_in : 7200
     */

    @JsonProperty("access_token")
    private String access;
    @JsonProperty("expires_in")
    private int expires;
    @JsonProperty("errcode")
    private int errcode;
    @JsonProperty("errmsg")
    private String errmsg;

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

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

    @Override
    public String toString() {
        return "WxAccessTokenResponse{" +
                "access='" + access + '\'' +
                ", expires=" + expires +
                ", errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
