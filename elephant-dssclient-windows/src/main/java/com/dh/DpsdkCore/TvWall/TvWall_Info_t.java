package com.dh.DpsdkCore.TvWall;

import com.dh.DpsdkCore.dpsdk_constant_value;

public class TvWall_Info_t {

	public int							nTvWallId;									// ����ǽID
	public int							nState;										// ����״̬
	public byte[]						szName = new byte[dpsdk_constant_value.DPSDK_CORE_TVWALL_NAME_LEN];			// ����
	
	public TvWall_Info_t()
	{
		
	}
}
