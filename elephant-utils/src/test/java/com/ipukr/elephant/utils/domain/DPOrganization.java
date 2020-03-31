package com.ipukr.elephant.utils.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ljx
 * <p>
 * Created by ljx wu on 2020-03-31 21:19
 */
@JsonIgnoreProperties(ignoreUnknown = true)
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@XmlRootElement(name = "Organization")
@XmlAccessorType(XmlAccessType.FIELD)
public class DPOrganization {
    @XmlElement(name = "Department")
    private Department Department;
    @XmlElement(name = "Devices")
    private Devices Devices;

    @XmlTransient
    public DPOrganization.Department getDepartment() {
        return Department;
    }

    public void setDepartment(DPOrganization.Department department) {
        Department = department;
    }
    @XmlTransient
    public DPOrganization.Devices getDevices() {
        return Devices;
    }

    public void setDevices(DPOrganization.Devices devices) {
        Devices = devices;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Builder
    public static class Department {
        @XmlAttribute(name = "coding")
//        @XmlAttribute
        private String coding;
//        @XmlAttribute
        @XmlAttribute(name = "name")
        private String name;
        private String modifytime;
        private String sn;
        private String memo;
        private String deptype;
        private String depsort;
        private String chargebooth;
        private String OrgNum;
        @XmlElement(name = "Device")
        private List<Device> Device;
        @XmlElement(name = "Channel")
        private List<Channel> Channel;

        @XmlTransient
        public String getCoding() {
            return coding;
        }

        public void setCoding(String coding) {
            this.coding = coding;
        }
        @XmlTransient
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }



        @JsonIgnoreProperties(ignoreUnknown = true)
//        @Data
//        @AllArgsConstructor
//        @NoArgsConstructor
//        @Builder
        public static class Device {
            @XmlAttribute(name = "id")
            private String id;
            @XmlTransient
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
//        @Data
//        @AllArgsConstructor
//        @NoArgsConstructor
//        @Builder
        public static class Channel {
            @XmlAttribute(name = "id")
            private String id;
            @XmlTransient
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Builder
    public static class Devices {
//        @JsonProperty(value = "Device")
        @XmlElement(name = "Device")
        private List<DeviceX> devices;

        @XmlTransient
        public List<DeviceX> getDevices() {
            return devices;
        }

        public void setDevices(List<DeviceX> devices) {
            this.devices = devices;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
//        @Data
//        @AllArgsConstructor
//        @NoArgsConstructor
//        @Builder
        public static class DeviceX  {
            @XmlAttribute(name = "id")
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
            @XmlElement(name = "UnitNodes")
            private List<UnitNodes> UnitNodes;

            @XmlTransient
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
            @XmlTransient
            public List<UnitNodes> getUnitNodes() {
                return UnitNodes;
            }

            public void setUnitNodes(List<UnitNodes> unitNodes) {
                UnitNodes = unitNodes;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
//            @Data
//            @AllArgsConstructor
//            @NoArgsConstructor
//            @Builder
            public static class UnitNodes {
                @XmlAttribute(name = "index")
                private String index;
                private String channelnum;
                @XmlAttribute(name = "type")
                private String type;
                @XmlElement(name = "Channel")
                private List<ChannelX> Channel;

                @XmlTransient
                public String getIndex() {
                    return index;
                }

                public void setIndex(String index) {
                    this.index = index;
                }

                @XmlTransient
                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public List<ChannelX> getChannel() {
                    return Channel;
                }

                public void setChannel(List<ChannelX> channel) {
                    Channel = channel;
                }

                @JsonIgnoreProperties(ignoreUnknown = true)
//                @Data
//                @AllArgsConstructor
//                @NoArgsConstructor
//                @Builder
                public static class ChannelX {
                    @XmlAttribute(name = "id")
                    private String id;
                    private String name;
                    private String desc;
                    private String status;
                    private String channelType;
                    private String channelSN;
                    private String rights;
                    private String alarmType;
                    private String alarmLevel;

                    @XmlTransient
                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }
                }
            }
        }
    }
}
