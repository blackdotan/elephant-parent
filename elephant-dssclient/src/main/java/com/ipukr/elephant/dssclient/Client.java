package com.ipukr.elephant.dssclient;

import com.ipukr.elephant.dssclient.domain.DPChannel;
import com.ipukr.elephant.dssclient.domain.DPDevice;
import com.ipukr.elephant.dssclient.domain.DPOrganization;
import com.ipukr.elephant.dssclient.domain.res.DPSnapshot;

import javax.xml.bind.JAXBException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2020/3/31.
 */
public interface Client {

	/**
	 * 获取组织树
	 * @return
	 */
	DPOrganization group() throws IOException, JAXBException;

	/**
	 * 通过设备id获取当前id下所有快照
	 * @param device
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	List<DPSnapshot> snapshot(DPDevice device) throws IOException, JAXBException;

	/**
	 * 通过设备id+通道id获取快照
	 * @param device
	 * @param channel
	 * @return
	 */
	DPSnapshot snapshot(DPDevice device, DPChannel channel);

	/**
	 * 通过 实时通道ID 请求实时码流
	 * @return
	 */
	Integer getReal(DPChannel channel, OutputStream ous);

	/**
	 * 关闭实时码流
	 * @return
	 */
	boolean closeReal(DPChannel channel);

	/** 注销
	 * @return
	 */
	boolean logout();

	/**
	 * 释放内存
	 * @return
	 */
	boolean destroy();
	/**
	 * 1.通过名称+组织树->获取整个设备对象
	 * 2.通过设备对象 返回对应的图片+通道channelId信息(snapshot()方法)
	 * @return
	 */
	DPDevice getDPDevice(String carName,DPOrganization organization);
}
