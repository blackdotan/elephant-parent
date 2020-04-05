package com.dh.DpsdkCore.TvWall;

import com.dh.DpsdkCore.dpsdk_constant_value;

public class TvWall_Screen_Info_t {

	public int							nScreenId;									// ��ID
	public byte[]						szName = new byte[dpsdk_constant_value.DPSDK_CORE_TVWALL_NAME_LEN];			// ����
	public byte[]						szDecoderId = new byte[dpsdk_constant_value.DPSDK_CORE_DEV_ID_LEN];			// ������ID
	public float						fLeft;										// ��߾�
	public float						fTop;										// �ϱ߾�
	public float						fWidth;										// ���
	public float						fHeight;									// �߶�
	public boolean						bBind;										// �Ƿ�󶨽�����
	
	public TvWall_Screen_Info_t()
	{
		
	}
}
