package com.ipukr.elephant.dssclient.third;

import com.dh.DpsdkCore.*;
import com.ipukr.elephant.dssclient.domain.DPChannel;
import com.ipukr.elephant.dssclient.domain.DPDevice;
import com.ipukr.elephant.dssclient.domain.DPSnapshot;

import java.util.concurrent.Callable;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2020/4/3.
 */
public class DPSSnapshotCallable implements Callable<DPSnapshot> {

	private int nPDLLHandle;
	private DPDevice device;
	private DPChannel channel;

	public DPSSnapshotCallable(int nPDLLHandle, DPDevice device, DPChannel channel) {
		this.nPDLLHandle = nPDLLHandle;
		this.device = device;
		this.channel = channel;
	}

	@Override
	public DPSnapshot call() throws Exception {
		System.out.println(String.format("--device.id=%d, offset=%d", device.getId(),  channel.getOffset()));
		return snapshot(device, channel);
	}


	public DPSnapshot snapshot(DPDevice device, DPChannel channel) {
		String szJson = "{ \"method\":\"dev.snap\",\"params\":{\"DevID\":" + device.getId() + ",\"DevChannel\":" + channel.getOffset() + ",\"PicNum\":4,\"SnapType\":2,\"CmdSrc\":1},\"id\":88 }";
		//模块
		int mdltype = dpsdk_mdl_type_e.DPSDK_MDL_DMS;
		//传输类型
		int trantype = generaljson_trantype_e.GENERALJSON_TRAN_REQUEST;
		//通过Json协议发送命令,返回结果通过DPSDK_SetGeneralJsonTransportCallback回调
		int nRet = IDpsdkCore.DPSDK_GeneralJsonTransport(nPDLLHandle, szJson.getBytes(), mdltype, trantype, 30 * 1000);
		StringBuilder sb = new StringBuilder();
		if (nRet == dpsdk_retval_e.DPSDK_RET_SUCCESS) {
			IDpsdkCore.DPSDK_SetGeneralJsonTransportCallback(nPDLLHandle, new fDPSDKGeneralJsonTransportCallback() {
				public void invoke(int nPDLLHandle, byte[] szJson) {
					sb.append(new String(szJson));
				}
			});
			System.out.printf("DPSDK_GeneralJsonTransport:成功，nRet = %d", nRet);
		} else {
			System.out.printf("DPSDK_GeneralJsonTransport:失败，nRet = %d", nRet);
		}
		return DPSnapshot.builder().channel(channel).data(sb.toString()).build();
	}
}
