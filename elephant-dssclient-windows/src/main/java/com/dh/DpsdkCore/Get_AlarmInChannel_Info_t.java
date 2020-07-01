package com.dh.DpsdkCore;

//��ȡ��������ͨ��������Ϣ
public class Get_AlarmInChannel_Info_t {
	public byte[]					szDeviceId = new byte[dpsdk_constant_value.DPSDK_CORE_DEV_ID_LEN];	// �豸ID
	public int						nAlarmInChannelCount;												// ͨ������
	public AlarmIn_Channel_Info_t[]	pAlarmInChannelnfo;													// ͨ����Ϣ

	public Get_AlarmInChannel_Info_t(int nMaxAlarmInChannelCount)
	{
		nAlarmInChannelCount = nMaxAlarmInChannelCount;
		pAlarmInChannelnfo = new AlarmIn_Channel_Info_t[nAlarmInChannelCount];	
		for(int i = 0;i < nAlarmInChannelCount;i++)
		{
			pAlarmInChannelnfo[i] = new AlarmIn_Channel_Info_t();
		}
	}
}
