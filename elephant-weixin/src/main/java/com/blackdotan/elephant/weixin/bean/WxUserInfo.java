package com.blackdotan.elephant.weixin.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.blackdotan.elephant.weixin.response.WxCode2SessionResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/10/31.
 */
@Getter
@Setter
public class WxUserInfo {


    /**
     * nickName : Band
     * gender : 1
     * language : zh_CN
     * city : Guangzhou
     * province : Guangdong
     * country : CN
     * avatarUrl : http://wx.qlogo.cn/mmopen/vi_32/1vZvI39NWFQ9XM4LtQpFrQJ1xlgZxx3w7bQxKARol6503Iuswjjn6nIGBiaycAjAtpujxyzYsrztuuICqIM5ibXQ/0
     */
    private String nickName;
    private int gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    @JsonIgnore
    private WxCode2SessionResponse wc2sr;
}
