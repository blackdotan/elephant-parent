package com.dh.DpsdkCore.TvWall;

import com.dh.DpsdkCore.dpsdk_constant_value;

public class TVWall_Task_Info_t {

	public int							nTaskId;																	// ����ID
	public byte[]						szName = new byte[dpsdk_constant_value.DPSDK_CORE_TVWALL_TASK_NAME_LEN];	// ��������
	public byte[]						szDes = new byte[dpsdk_constant_value.DPSDK_CORE_TVWALL_TASK_DES_LEN];		// ��������
	
	public TVWall_Task_Info_t()
	{
		
	}
}