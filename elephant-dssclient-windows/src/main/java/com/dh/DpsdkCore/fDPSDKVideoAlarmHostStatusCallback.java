package com.dh.DpsdkCore;

/** ��Ƶ��������������/��·״̬�ص�
@param	  IN	                                nPDLLHandle		    SDK���
@param	  IN	                                szDeviceId			�豸Id
@param	  IN	                                nChannelNO			ͨ���ţ��豸����ͨ������-1
@param	  IN	                                nStatus				״̬���ο�dpsdk_videoalarmhost_status_type_e
��Ƶ���������ڿͻ��˵�½��ʱ��DMS���ϱ�״̬���ͻ��˲�����֪ͨ�����ͻ��ˡ�
*/
public interface fDPSDKVideoAlarmHostStatusCallback {
	public void invoke(int nPDLLHandle, byte[] szDeviceId, int nChannelNO, int nStatus);
}
