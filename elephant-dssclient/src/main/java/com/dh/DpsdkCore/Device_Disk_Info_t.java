package com.dh.DpsdkCore;

/** �豸������Ϣ
@param   szDevId					�豸ID
@param   nDiskInfoSize				������Ϣ����
@param   pDiskInfoList				������Ϣ�б�
*/

public class Device_Disk_Info_t
{
	public byte[]				szDevId		=new byte[dpsdk_constant_value.DPSDK_CORE_DEV_ID_LEN];	//�豸ID
	public int					nDiskInfoSize;														//������Ϣ����
	public Single_Disk_Info_t	pDiskInfoList[];													//������Ϣ�б�
	
	public Device_Disk_Info_t(int nMaxCount)
	{
		nDiskInfoSize = nMaxCount;
		pDiskInfoList = new Single_Disk_Info_t[nDiskInfoSize];
		for(int i = 0; i < nDiskInfoSize; i++)
		{
			pDiskInfoList[i] = new Single_Disk_Info_t();
		}
	}
};