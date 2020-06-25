package com.dh.DpsdkCore;

/** ���籨������������/��·״̬�ص�
@param	  IN	                                nPDLLHandle		    SDK���
@param	  IN	                                szDeviceId			������ʱ���豸Id;��·/ȡ����·ʱ��ͨ��id
@param	  IN	                                nRType				�ϱ����ͣ��ο�dpsdk_netalarmhost_report_type_e��1������2��·
@param	  IN	                                nOperType			�������ͣ��ο�dpsdk_netalarmhost_operator_e��1�豸������2ͨ������
@param	  IN	                                nStatus				״̬�����ڷ���״̬1����2������������·״̬1��·2ȡ����·
���籨������״̬Ҫ�Լ���ѯ���ͻ��˲�����֪ͨ�����ͻ��ˡ�
*/
public interface fDPSDKNetAlarmHostStatusCallback {
	public void invoke(int nPDLLHandle, byte[] szDeviceId, int nRType, int nOperType, int nStatus);
}
