package com.dh.DpsdkCore;

/** ҵ��㲥ͨ��״̬�ص�
@param	  IN	                                nPDLLHandle		    SDK���
@param	  IN	                                szCameraId			ͨ��id
@param	  IN	                                nRType				�ϱ����ͣ��ο�dpsdk_broadcast_report_type_e��1500ҵ��㲥������Ϣ��1501ҵ��㲥����״̬��Ϣ
@param	  IN	                                nTime				�ϱ�ʱ��
@param	  IN	                                nStatus				״̬������ҵ��Խ�ͨ��������Ϣ 1���� 2��ʧ������ҵ��Խ�ͨ������״̬ 1���� 2ֹͣ
���籨������״̬Ҫ�Լ���ѯ���ͻ��˲�����֪ͨ�����ͻ��ˡ�
*/
public interface fDPSDKBroadcastReportStatusCallback {
	public void invoke(int nPDLLHandle, byte[] szCameraId, int nRType, long nTime, int nStatus);
}
