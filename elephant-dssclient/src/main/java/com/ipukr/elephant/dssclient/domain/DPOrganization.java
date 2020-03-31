package com.ipukr.elephant.dssclient.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ljx
 * <p>
 * Created by ljx wu on 2020-03-31 21:19
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DPOrganization {
    @JsonProperty(value = "Department")
    private Department Department;
    @JsonProperty(value = "Devices")
    private Devices Devices;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Department {

        private String coding;
        private String name;
        private String modifytime;
        private String sn;
        private String memo;
        private String deptype;
        private String depsort;
        private String chargebooth;
        private String OrgNum;
        @JsonProperty(value = "Devices")
        private List<Device> Device;
        @JsonProperty(value = "Channel")
        private List<Channel> Channel;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public static class Device {

            private String id;

        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public static class Channel {

            private String id;

        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Devices {
        @JsonProperty(value = "Device")
        private List<DeviceX> devices;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public static class DeviceX {
            private String id;
            private String type;
            private String name;
            private String manufacturer;
            private String model;
            private String ip;
            private String port;
            private String user;
            private String password;
            private String desc;
            private String status;
            private String logintype;
            private String registDeviceCode;
            private String proxyport;
            private String unitnum;
            private String deviceCN;
            private String deviceSN;
            private String deviceIp;
            private String devicePort;
            private String devMaintainer;
            private String devMaintainerPh;
            private String deviceLocation;
            private String deviceLocPliceStation;
            private String baudRate;
            private String comCode;
            @JsonProperty(value = "VideoType")
            private String VideoType;
            private String shopName;
            private String address;
            private String firstOwner;
            private String firstPosition;
            private String firstPhone;
            private String firstTel;
            private String serviceType;
            private String ownerGroup;
            private String belong;
            private String role;
            private String callNumber;
            private String rights;
            @JsonProperty(value = "UnitNodes")
            private UnitNodes UnitNodes;

            @JsonIgnoreProperties(ignoreUnknown = true)
            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            @Builder
            public static class UnitNodes {
                private String index;
                private String channelnum;
                private String type;
                private ChannelX Channel;

                @JsonIgnoreProperties(ignoreUnknown = true)
                @Data
                @AllArgsConstructor
                @NoArgsConstructor
                @Builder
                public static class ChannelX {
                    private String id;
                    private String name;
                    private String desc;
                    private String status;
                    private String channelType;
                    private String channelSN;
                    private String rights;
                    private String alarmType;
                    private String alarmLevel;

                }
            }
        }
    }
}
