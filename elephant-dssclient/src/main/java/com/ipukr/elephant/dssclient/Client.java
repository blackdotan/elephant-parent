package com.ipukr.elephant.dssclient;

import com.ipukr.elephant.dssclient.domain.DPChannel;
import com.ipukr.elephant.dssclient.domain.DPDevice;
import com.ipukr.elephant.dssclient.domain.DPOrganization;
import com.ipukr.elephant.dssclient.domain.res.DPSnapshot;

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
	DPOrganization group() throws IOException;

	/**
	 * 通过设备id获取当前id下所有快照
	 * @param DevID
	 * @return
	 */
	List<DPSnapshot> snapshot(DPDevice device) throws IOException;

	/**
	 * 通过设备id+通道id获取快照
	 * @param DevID
	 * @param DevChannel
	 * @return
	 */
	DPSnapshot snapshot(DPDevice device, DPChannel channel);

	/**
	 * 通过 实时通道ID 请求实时码流
	 * @return
	 */
	Integer getReal(String RealDevChannelID, OutputStream ous);

	/**
	 * 关闭实时码流
	 * @return
	 */
	boolean closeReal(Integer nRealSeq);

	/** 注销
	 * @return
	 */
	boolean logout();

	/**
	 * 释放内存
	 * @return
	 */
	boolean destroy();
}
