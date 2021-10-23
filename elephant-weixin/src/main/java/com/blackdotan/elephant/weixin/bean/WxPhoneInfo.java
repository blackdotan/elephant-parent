package com.blackdotan.elephant.weixin.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WxPhoneInfo {

    /**
     * phoneNumber : 18800000000
     * watermark : {"appid":"wx2ba363fc4454f27c","timestamp":1586333901}
     * purePhoneNumber : 18800000000
     * countryCode : 86
     */

    private String phoneNumber;
    private WatermarkBean watermark;
    private String purePhoneNumber;
    private String countryCode;


    @Getter
    @Setter
    public static class WatermarkBean {
        /**
         * appid : wx2ba363fc4454f27c
         * timestamp : 1586333901
         */

        private String appid;

        private int timestamp;

    }
}
