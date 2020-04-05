package com.dh.DpsdkCore;

public class SetDoorCmd_Request_t {

	public byte[]					szCameraId= new byte[dpsdk_constant_value.DPSDK_CORE_CHL_ID_LEN];			// ͨ��ID
	public int						cmd;									// �������� �ο�dpsdk_SetDoorCmd_e
	public long						start;									// ��ʼʱ��
	public long						end;									// ����ʱ��
	
	public SetDoorCmd_Request_t()
	{
		
	}
}
