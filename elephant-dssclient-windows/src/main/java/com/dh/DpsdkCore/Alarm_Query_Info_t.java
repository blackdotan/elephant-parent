package com.dh.DpsdkCore;

public class Alarm_Query_Info_t {
	
	public byte[]						szCameraId = new byte[dpsdk_constant_value.DPSDK_CORE_CHL_ID_LEN];			// 通道ID
	public long							uStartTime;									// 开始时间
	public long							uEndTime;									// 结束时间
	public int      					nAlarmType;									// 报警类型  dpsdk_alarm_type_e
	public boolean						bLinkInfo;									// 返回的报警信息是否有联动录像信息，用于兼容部分老平台
	public boolean						bPositionInfo;								// 返回的报警信息是否有点位信息，用于兼容新版本ADS新增点位信息
	
	public Alarm_Query_Info_t()
	{
		
	}
}
