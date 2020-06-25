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

    public static class Department {
        @XmlAttribute(name = "coding")
        private String coding;
        @XmlAttribute(name = "name")
        private String name;
        @XmlAttribute(name = "modifytime")
        private String modifytime;
        @XmlAttribute(name = "sn")
        private String sn;
        @XmlAttribute(name = "memo")
        private String memo;
        @XmlAttribute(name = "deptype")
        private String deptype;
        @XmlAttribute(name = "depsort")
        private String depsort;
        @XmlAttribute(name = "chargebooth")
        private String chargebooth;
        @XmlAttribute(name = "OrgNum")
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

        @XmlTransient
        public String getModifytime() {
            return modifytime;
        }

        public void setModifytime(String modifytime) {
            this.modifytime = modifytime;
        }

        @XmlTransient
        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        @XmlTransient
        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        @XmlTransient
        public String getDeptype() {
            return deptype;
        }

        public void setDeptype(String deptype) {
            this.deptype = deptype;
        }

        @XmlTransient
        public String getDepsort() {
            return depsort;
        }

        public void setDepsort(String depsort) {
            this.depsort = depsort;
        }

        @XmlTransient
        public String getChargebooth() {
            return chargebooth;
        }

        public void setChargebooth(String chargebooth) {
            this.chargebooth = chargebooth;
        }

        @XmlTransient
        public String getOrgNum() {
            return OrgNum;
        }

        public void setOrgNum(String orgNum) {
            OrgNum = orgNum;
        }

        @XmlTransient
        public List<DPOrganization.Department.Device> getDevice() {
            return Device;
        }

        public void setDevice(List<DPOrganization.Department.Device> device) {
            Device = device;
        }

        @XmlTransient
        public List<DPOrganization.Department.Channel> getChannel() {
            return Channel;
        }

        public void setChannel(List<DPOrganization.Department.Channel> channel) {
            Channel = channel;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)

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

    public static class Devices {
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
        public static class DeviceX  {
            @XmlAttribute(name = "id")
            private String id;
            @XmlAttribute(name = "type")
            private String type;
            @XmlAttribute(name = "name")
            private String name;
            @XmlAttribute(name = "manufacturer")
            private String manufacturer;
            @XmlAttribute(name = "model")
            private String model;
            @XmlAttribute(name = "ip")
            private String ip;
            @XmlAttribute(name = "port")
            private String port;
            @XmlAttribute(name = "user")
            private String user;
            @XmlAttribute(name = "password")
            private String password;
            @XmlAttribute(name = "desc")
            private String desc;
            @XmlAttribute(name = "status")
            private String status;
            @XmlAttribute(name = "logintype")
            private String logintype;
            @XmlAttribute(name = "registDeviceCode")
            private String registDeviceCode;
            @XmlAttribute(name = "proxyport")
            private String proxyport;
            @XmlAttribute(name = "unitnum")
            private String unitnum;
            @XmlAttribute(name = "deviceCN")
            private String deviceCN;
            @XmlAttribute(name = "deviceSN")
            private String deviceSN;
            @XmlAttribute(name = "deviceIp")
            private String deviceIp;
            @XmlAttribute(name = "devicePort")
            private String devicePort;
            @XmlAttribute(name = "devMaintainer")
            private String devMaintainer;
            @XmlAttribute(name = "devMaintainerPh")
            private String devMaintainerPh;
            @XmlAttribute(name = "deviceLocation")
            private String deviceLocation;
            @XmlAttribute(name = "deviceLocPliceStation")
            private String deviceLocPliceStation;
            @XmlAttribute(name = "baudRate")
            private String baudRate;
            @XmlAttribute(name = "comCode")
            private String comCode;
            @JsonProperty(value = "VideoType")
            @XmlAttribute(name = "VideoType")
            private String VideoType;
            @XmlAttribute(name = "shopName")
            private String shopName;
            @XmlAttribute(name = "address")
            private String address;
            @XmlAttribute(name = "firstOwner")
            private String firstOwner;
            @XmlAttribute(name = "firstPosition")
            private String firstPosition;
            @XmlAttribute(name = "firstPhone")
            private String firstPhone;
            @XmlAttribute(name = "firstTel")
            private String firstTel;
            @XmlAttribute(name = "serviceType")
            private String serviceType;
            @XmlAttribute(name = "ownerGroup")
            private String ownerGroup;
            @XmlAttribute(name = "belong")
            private String belong;
            @XmlAttribute(name = "role")
            private String role;
            @XmlAttribute(name = "callNumber")
            private String callNumber;
            @XmlAttribute(name = "rights")
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

            @XmlTransient
            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            @XmlTransient
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @XmlTransient
            public String getManufacturer() {
                return manufacturer;
            }

            public void setManufacturer(String manufacturer) {
                this.manufacturer = manufacturer;
            }

            @XmlTransient
            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            @XmlTransient
            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            @XmlTransient
            public String getPort() {
                return port;
            }

            public void setPort(String port) {
                this.port = port;
            }

            @XmlTransient
            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            @XmlTransient
            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            @XmlTransient
            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            @XmlTransient
            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            @XmlTransient
            public String getLogintype() {
                return logintype;
            }

            public void setLogintype(String logintype) {
                this.logintype = logintype;
            }

            @XmlTransient
            public String getRegistDeviceCode() {
                return registDeviceCode;
            }

            public void setRegistDeviceCode(String registDeviceCode) {
                this.registDeviceCode = registDeviceCode;
            }

            @XmlTransient
            public String getProxyport() {
                return proxyport;
            }

            public void setProxyport(String proxyport) {
                this.proxyport = proxyport;
            }

            @XmlTransient
            public String getUnitnum() {
                return unitnum;
            }

            public void setUnitnum(String unitnum) {
                this.unitnum = unitnum;
            }

            @XmlTransient
            public String getDeviceCN() {
                return deviceCN;
            }

            public void setDeviceCN(String deviceCN) {
                this.deviceCN = deviceCN;
            }

            @XmlTransient
            public String getDeviceSN() {
                return deviceSN;
            }

            public void setDeviceSN(String deviceSN) {
                this.deviceSN = deviceSN;
            }

            @XmlTransient
            public String getDeviceIp() {
                return deviceIp;
            }

            public void setDeviceIp(String deviceIp) {
                this.deviceIp = deviceIp;
            }

            @XmlTransient
            public String getDevicePort() {
                return devicePort;
            }

            public void setDevicePort(String devicePort) {
                this.devicePort = devicePort;
            }

            @XmlTransient
            public String getDevMaintainer() {
                return devMaintainer;
            }

            public void setDevMaintainer(String devMaintainer) {
                this.devMaintainer = devMaintainer;
            }

            @XmlTransient
            public String getDevMaintainerPh() {
                return devMaintainerPh;
            }

            public void setDevMaintainerPh(String devMaintainerPh) {
                this.devMaintainerPh = devMaintainerPh;
            }

            @XmlTransient
            public String getDeviceLocation() {
                return deviceLocation;
            }

            public void setDeviceLocation(String deviceLocation) {
                this.deviceLocation = deviceLocation;
            }

            @XmlTransient
            public String getDeviceLocPliceStation() {
                return deviceLocPliceStation;
            }

            public void setDeviceLocPliceStation(String deviceLocPliceStation) {
                this.deviceLocPliceStation = deviceLocPliceStation;
            }

            @XmlTransient
            public String getBaudRate() {
                return baudRate;
            }

            public void setBaudRate(String baudRate) {
                this.baudRate = baudRate;
            }

            @XmlTransient
            public String getComCode() {
                return comCode;
            }

            public void setComCode(String comCode) {
                this.comCode = comCode;
            }

            @XmlTransient
            public String getVideoType() {
                return VideoType;
            }

            public void setVideoType(String videoType) {
                VideoType = videoType;
            }

            @XmlTransient
            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            @XmlTransient
            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            @XmlTransient
            public String getFirstOwner() {
                return firstOwner;
            }

            public void setFirstOwner(String firstOwner) {
                this.firstOwner = firstOwner;
            }

            @XmlTransient
            public String getFirstPosition() {
                return firstPosition;
            }

            public void setFirstPosition(String firstPosition) {
                this.firstPosition = firstPosition;
            }

            @XmlTransient
            public String getFirstPhone() {
                return firstPhone;
            }

            public void setFirstPhone(String firstPhone) {
                this.firstPhone = firstPhone;
            }

            @XmlTransient
            public String getFirstTel() {
                return firstTel;
            }

            public void setFirstTel(String firstTel) {
                this.firstTel = firstTel;
            }

            @XmlTransient
            public String getServiceType() {
                return serviceType;
            }

            public void setServiceType(String serviceType) {
                this.serviceType = serviceType;
            }

            @XmlTransient
            public String getOwnerGroup() {
                return ownerGroup;
            }

            public void setOwnerGroup(String ownerGroup) {
                this.ownerGroup = ownerGroup;
            }

            @XmlTransient
            public String getBelong() {
                return belong;
            }

            public void setBelong(String belong) {
                this.belong = belong;
            }

            @XmlTransient
            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            @XmlTransient
            public String getCallNumber() {
                return callNumber;
            }

            public void setCallNumber(String callNumber) {
                this.callNumber = callNumber;
            }

            @XmlTransient
            public String getRights() {
                return rights;
            }

            public void setRights(String rights) {
                this.rights = rights;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class UnitNodes {
                @XmlAttribute(name = "index")
                private String index;
                @XmlAttribute(name = "channelnum")
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

                @XmlTransient
                public List<ChannelX> getChannel() {
                    return Channel;
                }

                public void setChannel(List<ChannelX> channel) {
                    Channel = channel;
                }


                @XmlTransient
                public String getChannelnum() {
                    return channelnum;
                }

                public void setChannelnum(String channelnum) {
                    this.channelnum = channelnum;
                }

                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class ChannelX {
                    @XmlAttribute(name = "id")
                    private String id;
                    @XmlAttribute(name = "name")
                    private String name;
                    @XmlAttribute(name = "desc")
                    private String desc;
                    @XmlAttribute(name = "status")
                    private String status;
                    @XmlAttribute(name = "channelType")
                    private String channelType;
                    @XmlAttribute(name = "channelSN")
                    private String channelSN;
                    @XmlAttribute(name = "rights")
                    private String rights;
                    @XmlAttribute(name = "alarmType")
                    private String alarmType;
                    @XmlAttribute(name = "alarmLevel")
                    private String alarmLevel;

                    @XmlTransient
                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    @XmlTransient
                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    @XmlTransient
                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    @XmlTransient
                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    @XmlTransient
                    public String getChannelType() {
                        return channelType;
                    }

                    public void setChannelType(String channelType) {
                        this.channelType = channelType;
                    }

                    @XmlTransient
                    public String getChannelSN() {
                        return channelSN;
                    }

                    public void setChannelSN(String channelSN) {
                        this.channelSN = channelSN;
                    }

                    @XmlTransient
                    public String getRights() {
                        return rights;
                    }

                    public void setRights(String rights) {
                        this.rights = rights;
                    }

                    @XmlTransient
                    public String getAlarmType() {
                        return alarmType;
                    }

                    public void setAlarmType(String alarmType) {
                        this.alarmType = alarmType;
                    }

                    @XmlTransient
                    public String getAlarmLevel() {
                        return alarmLevel;
                    }

                    public void setAlarmLevel(String alarmLevel) {
                        this.alarmLevel = alarmLevel;
                    }
                }
            }
        }
    }
}
