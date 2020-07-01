package com.ipukr.elephant.dssclient.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ljx
 * <p>
 * Created by ljx wu on 2020-03-31 19:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DPDevice {

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

    private List<DPChannel> channels;

}
