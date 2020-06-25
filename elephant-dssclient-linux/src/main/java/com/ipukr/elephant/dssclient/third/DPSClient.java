package com.ipukr.elephant.dssclient.third;

import com.dh.DpsdkCore.*;
import com.ipukr.elephant.dssclient.Client;
import com.ipukr.elephant.dssclient.config.DPSClientConfig;
import com.ipukr.elephant.dssclient.domain.DPChannel;
import com.ipukr.elephant.dssclient.domain.DPDevice;
import com.ipukr.elephant.dssclient.domain.DPOrganization;
import com.ipukr.elephant.dssclient.domain.DPSnapshot;
import com.ipukr.elephant.utils.JaxbUtils;

import javax.xml.bind.JAXBException;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2020/3/31.
 */
public class DPSClient implements Client {

	protected DPSClientConfig config;

	protected DPOrganization organization;
	// TODO 理清句柄应用的逻辑
	protected int m_nDLLHandle = -1;

	public DPSClient(DPSClientConfig config) {
		this.config = config;
		this.init();
		this.login();
	}


	private fDPSDKGeneralJsonTransportCallback fGeneralJson = new fDPSDKGeneralJsonTransportCallback() {
		@Override
		public void invoke(int nPDLLHandle, byte[] szJson) {
			// TODO 使用日志工具打印日志
			System.out.printf("General Json Return, ReturnJson = %s", new String(szJson));
			System.out.println();
		}
	};

	private void init() {
		int nRet = -1;
		Return_Value_Info_t res = new Return_Value_Info_t();
		nRet = IDpsdkCore.DPSDK_Create(dpsdk_sdk_type_e.DPSDK_CORE_SDK_SERVER, res);

		m_nDLLHandle = res.nReturnValue;
//		String dpsdklog = "D:\\dpsdkjavalog";
//		nRet = IDpsdkCore.DPSDK_SetLog(m_nDLLHandle, dpsdklog.getBytes());
//		String dumpfile = "D:\\dpsdkjavadump";
//		nRet = IDpsdkCore.DPSDK_StartMonitor(m_nDLLHandle, dumpfile.getBytes());
		if (m_nDLLHandle > 0) {
//			//设置设备状态上报监听函数
//			nRet = IDpsdkCore.DPSDK_SetDPSDKDeviceStatusCallback(m_nDLLHandle, fDeviceStatus);
//			//设置NVR通道状态上报监听函数
//			nRet =IDpsdkCore.DPSDK_SetDPSDKNVRChnlStatusCallback(m_nDLLHandle, fNVRChnlStatus);
			//设置通用JSON回调
			nRet = IDpsdkCore.DPSDK_SetGeneralJsonTransportCallback(m_nDLLHandle, fGeneralJson);
//			//设置卡口过车信息（不带图片）上报回调
//			nRet = IDpsdkCore.DPSDK_SetDPSDKGetBayCarInfoCallbackEx(m_nDLLHandle, fBayCarInfo);
//			//设置违章报警回调
//			nRet = IDpsdkCore.DPSDK_SetDPSDKTrafficAlarmCallback(m_nDLLHandle, fTrafficAlarmCallback);
//			//设置区间测速上报回调
//			nRet = IDpsdkCore.DPSDK_SetDPSDKGetAreaSpeedDetectCallback(m_nDLLHandle, fGetAreaSpeedDetectCallback);
		}

		System.out.print("创建DPSDK, 返回 m_nDLLHandle = ");
		System.out.println(m_nDLLHandle);
	}

	private boolean login() {
		Login_Info_t loginInfo = new Login_Info_t();
		loginInfo.szIp = config.getIp().getBytes();
		loginInfo.nPort = config.getPort();
		loginInfo.szUsername = config.getUser().getBytes();
		loginInfo.szPassword = config.getPass().getBytes();

		// 1.一代协议  2.二代协议
		loginInfo.nProtocol = dpsdk_protocol_version_e.DPSDK_PROTOCOL_VERSION_II;
		//// 登陆类型，1为PC客户端, 2为手机客户端
		loginInfo.iType = 1;

		int nRet = IDpsdkCore.DPSDK_Login(m_nDLLHandle, loginInfo, 10000);
		if (nRet == dpsdk_retval_e.DPSDK_RET_SUCCESS) {
			System.out.printf("登录成功，nRet = %d\n", nRet);
			return true;
		} else {
			System.out.printf("登录失败，nRet = %d\n", nRet);
		}
		System.out.println();
		return false;
	}


	/**
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	@Override
	public DPOrganization fetgroup() throws IOException, JAXBException {
		Return_Value_Info_t nGroupLen = new Return_Value_Info_t();
		int nRet = IDpsdkCore.DPSDK_LoadDGroupInfo(m_nDLLHandle, nGroupLen, 180000);
		if (nRet == dpsdk_retval_e.DPSDK_RET_SUCCESS) {
//			System.out.printf("加载所有组织树成功，nRet = %d， nDepCount = %d", nRet, nGroupLen.nReturnValue);
			byte[] szGroupBuf = new byte[nGroupLen.nReturnValue];
			int nRst = IDpsdkCore.DPSDK_GetDGroupStr(m_nDLLHandle, szGroupBuf, nGroupLen.nReturnValue, 10000);

			if (nRst == dpsdk_retval_e.DPSDK_RET_SUCCESS) {
//				System.out.println(new String(szGroupBuf));
				String xml = new String(szGroupBuf);
				BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(szGroupBuf));

//				XmlMapper mapper = new XmlMapper();
//				JSONObject jsonObject = XML.toJSONObject(xml .replace("\r", "").replace("\t", ""));

//				String str = JsonUtils.parserObj2String(jsonObject.toString());
//				Map iMap = mapper.readValue(xml, Map.class);
//				System.out.println(JsonUtils.parserObj2String(iMap));
//				Organization iOrganizatio = mapper.readValue(xml, Organization.class);
//				System.out.println(JsonUtils.parserObj2String(iOrganizatio));

				this.organization = JaxbUtils.xmlToBean(bis, DPOrganization.class);
//				this.organization = mapper.readValue(xml, DPOrganization.class);
//						.replace("\r", "")
//						.replace("\n", "")
//						.replace("\t", "");
//
//				FileOutputStream fous = new FileOutputStream("D:\\2111.txt");
//				fous.write(strtri.getBytes());
//				fous.flush();
//				fous.close();

//				ObjectMapper mapper = new ObjectMapper();
//				mapper.readValue(strtri.getBytes(), DPOrganization.class);

//				this.organization = JsonUtils.parserString2Obj(strtri.trim(), DPOrganization.class);
				return organization;
			}

		} else {
//			System.out.printf("加载所有组织树失败，nRet = %d", nRet);
		}
//		System.out.println();
		return null;
	}

	@Override
	public List<DPSnapshot> snapshot(DPDevice device) throws Exception {
		if (this.organization == null) {
			this.fetgroup();
			//还加载不出来
			if(this.organization == null){
				throw new IOException("未登录，或服务器未开启");
			}
		}
		List<DPSnapshot> arr = new ArrayList<>();

		List<DPOrganization.Devices.DeviceX.UnitNodes> uns = organization.getDevices().getDevices().stream().filter(e -> {
//			System.out.println(e.getId());
			return e.getId() == device.getId();
		}).flatMap(e-> {
//			System.out.println(e.getUnitNodes());
			return e.getUnitNodes().stream();
		}).filter(n-> {
//			System.out.println(n.getType());
			return n.getType() == 1;
		}).collect(Collectors.toList());
		int offset = 0;
		for ( DPOrganization.Devices.DeviceX.UnitNodes.ChannelX channelX : uns.stream().flatMap(e ->e.getChannel().stream()).collect(Collectors.toList())) {
			DPChannel channel = DPChannel.builder()
					.id(channelX.getId())
					.offset(offset++)
					.build();

			DPSnapshot snapshot = snapshot(device, channel);
			arr.add(snapshot);
		}
		return arr;
	}

	@Override
	public DPSnapshot snapshot(DPDevice device, DPChannel channel) {
		String szJson = "{ \"method\":\"dev.snap\",\"params\":{\"DevID\":" + device.getId() + ",\"DevChannel\":" + channel.getOffset() + ",\"PicNum\":4,\"SnapType\":2,\"CmdSrc\":1},\"id\":88 }";
		//模块
		int mdltype = dpsdk_mdl_type_e.DPSDK_MDL_DMS;
		//传输类型
		int trantype = generaljson_trantype_e.GENERALJSON_TRAN_REQUEST;
		//通过Json协议发送命令,返回结果通过DPSDK_SetGeneralJsonTransportCallback回调
		int nRet = IDpsdkCore.DPSDK_GeneralJsonTransport(m_nDLLHandle, szJson.getBytes(), mdltype, trantype, 30 * 1000);
		StringBuilder sb = new StringBuilder();
		DPSnapshot snapshot = DPSnapshot.builder().channel(channel).build();
		if (nRet == dpsdk_retval_e.DPSDK_RET_SUCCESS) {
			IDpsdkCore.DPSDK_SetGeneralJsonTransportCallback(m_nDLLHandle, new fDPSDKGeneralJsonTransportCallback() {
				@Override
				public void invoke(int nPDLLHandle, byte[] szJson) {
					snapshot.append(szJson);
					System.out.println(new String(szJson));
					snapshot.setData(new String(szJson));
				}
			});
			System.out.printf("DPSDK_GeneralJsonTransport:成功，nRet = %d", nRet);
		} else {
			System.out.printf("DPSDK_GeneralJsonTransport:失败，nRet = %d", nRet);
		}
		System.out.println();
		return snapshot;
//		return sb.toString();
	}

	/**
	 * @param channel
	 * @param ous
	 * @return
	 */
	@Override
	public Integer openreal(DPChannel channel, OutputStream ous) {
		Return_Value_Info_t nRealSeq = new Return_Value_Info_t();
		Get_RealStream_Info_t getInfo = new Get_RealStream_Info_t();
		getInfo.szCameraId = channel.getId().getBytes();// 通道ID
		getInfo.nStreamType = dpsdk_stream_type_e.DPSDK_CORE_STREAMTYPE_MAIN;// 码流类型（1.主码流  2.辅码流）
		getInfo.nRight = dpsdk_check_right_e.DPSDK_CORE_NOT_CHECK_RIGHT; //0.检查  1.不检查权限，请求视频流，无需加载组织结构
		getInfo.nMediaType = dpsdk_media_type_e.DPSDK_CORE_MEDIATYPE_VIDEO;// 媒体类型：1.视频 2.音频 3.视频+音频
		getInfo.nTransType = dpsdk_trans_type_e.DPSDK_CORE_TRANSTYPE_TCP;//传输类型 0.UDP 1.TCP
		getInfo.nTrackID = 701;
//		fMediaDataCallback m_MediaCB = new DPSDKMediaDataCallback();//请求实时码流，成功后视频码流进入m_MediaCB函数中

		fMediaDataCallback m_MediaCB = new fMediaDataCallback() {
			@Override
			public void invoke(int nPDLLHandle, int nSeq, int nMediaType, byte[] szNodeId, int nParamVal, byte[] szData, int nDataLen) {
				System.out.printf("视频流：DPSDKMediaDataCallback nSeq = %d, nMediaType = %d, nDataLen = %d", nSeq, nMediaType, nDataLen);
				System.out.println();
				try {
					if (nDataLen == 0) {
						ous.flush();
					}
					ous.write(szData);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		int nRet = IDpsdkCore.DPSDK_GetRealStream(m_nDLLHandle, nRealSeq, getInfo, m_MediaCB, 10000);

		if (nRet == dpsdk_retval_e.DPSDK_RET_SUCCESS) {
			System.out.printf("打开实时视频成功，nRet = %d， nSeq = %d", nRet, nRealSeq.nReturnValue);
			System.out.println();
		} else {
			System.out.printf("打开实时视频失败，nRet = %d", nRet);
		}
		if (nRet == dpsdk_retval_e.DPSDK_RET_SUCCESS) {
			return nRealSeq.nReturnValue;
		} else {
			return -1;
		}
	}

	/**
	 * @param channel
	 * @return
	 */
	@Override
	public boolean closereal(DPChannel channel) {
		int nsequence = channel.getNsequence();
		if (nsequence != -1) {
			int nRet = IDpsdkCore.DPSDK_CloseRealStreamBySeq(m_nDLLHandle, nsequence, 10000);
			if (nRet == dpsdk_retval_e.DPSDK_RET_SUCCESS) {
				System.out.printf("关闭实时视频成功，nRet = %d， nSeq = %d", nRet, nsequence);
				return true;
			} else {
				System.out.printf("关闭实时视频失败，nRet = %d", nRet);
				return false;
			}
		} else {
			return true;
		}
	}

//	@Override
//	public boolean logout() {
//		int nRet = IDpsdkCore.DPSDK_Logout(m_nDLLHandle, 10000);
//		if (nRet == dpsdk_retval_e.DPSDK_RET_SUCCESS) {
//			System.out.printf("登出成功，nRet = %d", nRet);
//			return true;
//		} else {
//			System.out.printf("登出失败，nRet = %d", nRet);
//		}
//		System.out.println();
//		return false;
//	}

	@Override
	public boolean destroy() {
		int nRet = IDpsdkCore.DPSDK_Destroy(m_nDLLHandle);
		if (nRet == dpsdk_retval_e.DPSDK_RET_SUCCESS) {
			System.out.printf("释放内存成功，nRet = %d", nRet);
			return true;
		} else {
			System.out.printf("释放内存失败，nRet = %d", nRet);
		}
		System.out.println();
		return false;
	}



}
