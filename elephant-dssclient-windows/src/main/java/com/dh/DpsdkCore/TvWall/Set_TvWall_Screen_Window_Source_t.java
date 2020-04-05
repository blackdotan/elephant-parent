package com.dh.DpsdkCore.TvWall;

import com.dh.DpsdkCore.dpsdk_constant_value;

public class Set_TvWall_Screen_Window_Source_t {

	public int						nTvWallId;									// ����ǽID
	public int						nScreenId;									// ��ID
	public int						nWindowId;									// ����ID
	public byte[]					szCameraId= new byte[dpsdk_constant_value.DPSDK_CORE_CHL_ID_LEN];			// ͨ��ID
	public int				        enStreamType;								// ��������,�ο���dpsdk_stream_type_e����
	public long						nStayTime;									// ͣ��ʱ��
	
	public Set_TvWall_Screen_Window_Source_t()
	{
		
	}
}
