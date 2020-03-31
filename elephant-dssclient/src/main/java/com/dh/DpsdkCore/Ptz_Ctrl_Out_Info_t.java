package com.dh.DpsdkCore;

public class Ptz_Ctrl_Out_Info_t {
	public byte[]	szCameraId = new byte[dpsdk_constant_value.DPSDK_CORE_CHL_ID_LEN];	// ͨ��ID
	public boolean							bOpen;                                      //�򿪱�־��true-��, false-�ر�
	public int								nCmd;                                       //�������״̬���ơ�1=������0=�ر�     ģʽ���ƣ�0=�ر�  1=�Զ� 2=�ֶ� 3����ģʽ�¹رգ�4����ģʽ�ֶ�
	public int								nType;                                      //�������͡�1״̬���� 2 ģʽ����
}
