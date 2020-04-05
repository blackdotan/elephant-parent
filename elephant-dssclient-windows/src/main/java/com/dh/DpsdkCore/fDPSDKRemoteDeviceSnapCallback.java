package com.dh.DpsdkCore;

/** �����豸Զ��ץ�Ļص�
@param   IN           nPDLLHandle               SDK���
@param   IN           szCameraId                ͨ��ID
@param   IN           szFullPath                �ļ�ȫ·����
*/
public interface fDPSDKRemoteDeviceSnapCallback {
	public void invoke(int nPDLLHandle, byte[] szCameraId, byte[] szFullPath);
}
