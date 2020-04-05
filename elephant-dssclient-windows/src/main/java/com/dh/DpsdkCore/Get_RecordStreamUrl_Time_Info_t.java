package com.dh.DpsdkCore;

/** 按时间获取录像url
@param   szCameraId					通道ID
@param   nSource					录像源类型 ,参考类dpsdk_recsource_type_e定义   
@param   uBeginTime					播放起始
@param   uEndTime					播放结束
@param   szUrl						URL信息
@param	 nSessionId					cms sessionid
@param	 nToken						令牌
*/

public class Get_RecordStreamUrl_Time_Info_t
{
	public byte[]	szCameraId	= new byte[dpsdk_constant_value.DPSDK_CORE_CHL_ID_LEN];		// 通道ID
	public int		nSource;																// 录像源类型 ,参考类dpsdk_recsource_type_e定义                 
	public long		uBeginTime;																// 开始时间 
	public long		uEndTime;																// 结束时间
	public byte[]	szUrl		= new byte[dpsdk_constant_value.DPSDK_TAG_URL_LEN];			// URL信息
	public int		nSessionId;																// cms sessionid
	public int		nToken;																	// 令牌
};