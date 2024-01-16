package com.blackdotan.elephant.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExpressAddress implements Serializable {
    private String uuid;

    private String owner;

    private String city;

    private String province;

    private String district;

    private Integer status;

    private String address;

    private String addresseeName;

    private String addresseePhone;

    private Double lng;

    private Double lat;

    private Date updTime;

    private Date crtTime;

    private Map params = new HashMap<Object,Object>();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAddresseeName() {
        return addresseeName;
    }

    public void setAddresseeName(String addresseeName) {
        this.addresseeName = addresseeName == null ? null : addresseeName.trim();
    }

    public String getAddresseePhone() {
        return addresseePhone;
    }

    public void setAddresseePhone(String addresseePhone) {
        this.addresseePhone = addresseePhone == null ? null : addresseePhone.trim();
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExpressAddress [");
        sb.append("uuid="); if (this.getUuid() == null) sb.append("null"); else sb.append('"').append(this.getUuid()).append('"');
        sb.append(", ").append("owner="); if (this.getOwner() == null) sb.append("null"); else sb.append('"').append(this.getOwner()).append('"');
        sb.append(", ").append("city="); if (this.getCity() == null) sb.append("null"); else sb.append('"').append(this.getCity()).append('"');
        sb.append(", ").append("province="); if (this.getProvince() == null) sb.append("null"); else sb.append('"').append(this.getProvince()).append('"');
        sb.append(", ").append("district="); if (this.getDistrict() == null) sb.append("null"); else sb.append('"').append(this.getDistrict()).append('"');
        sb.append(", ").append("status=").append(this.getStatus());
        sb.append(", ").append("address="); if (this.getAddress() == null) sb.append("null"); else sb.append('"').append(this.getAddress()).append('"');
        sb.append(", ").append("addresseeName="); if (this.getAddresseeName() == null) sb.append("null"); else sb.append('"').append(this.getAddresseeName()).append('"');
        sb.append(", ").append("addresseePhone="); if (this.getAddresseePhone() == null) sb.append("null"); else sb.append('"').append(this.getAddresseePhone()).append('"');
        sb.append(", ").append("lng=").append(this.getLng());
        sb.append(", ").append("lat=").append(this.getLat());
        sb.append(", ").append("updTime=").append(this.getUpdTime());
        sb.append(", ").append("crtTime=").append(this.getCrtTime());
        sb.append("]");
        return sb.toString();
    }

    public void setParams(Map params) {
        this.params=params;
    }

    public Map getParams() {
        return this.params;
    }

    public static class Builder {
        private ExpressAddress obj;

        public Builder() {
            this.obj = new ExpressAddress();
        }

        public Builder uuid(String uuid) {
            obj.uuid = uuid;
            return this;
        }

        public Builder owner(String owner) {
            obj.owner = owner;
            return this;
        }

        public Builder city(String city) {
            obj.city = city;
            return this;
        }

        public Builder province(String province) {
            obj.province = province;
            return this;
        }

        public Builder district(String district) {
            obj.district = district;
            return this;
        }

        public Builder status(Integer status) {
            obj.status = status;
            return this;
        }

        public Builder address(String address) {
            obj.address = address;
            return this;
        }

        public Builder addresseeName(String addresseeName) {
            obj.addresseeName = addresseeName;
            return this;
        }

        public Builder addresseePhone(String addresseePhone) {
            obj.addresseePhone = addresseePhone;
            return this;
        }

        public Builder lng(Double lng) {
            obj.lng = lng;
            return this;
        }

        public Builder lat(Double lat) {
            obj.lat = lat;
            return this;
        }

        public Builder updTime(Date updTime) {
            obj.updTime = updTime;
            return this;
        }

        public Builder crtTime(Date crtTime) {
            obj.crtTime = crtTime;
            return this;
        }

        public Builder params(Map params) {
            obj.params = params;
            return this;
        }

        public ExpressAddress build() {
            return this.obj;
        }
    }
}
