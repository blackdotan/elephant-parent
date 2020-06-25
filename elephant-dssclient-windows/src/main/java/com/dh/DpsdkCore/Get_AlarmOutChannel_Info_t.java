package com.dh.DpsdkCore;

public class Get_AlarmOutChannel_Info_t {
	
	public byte[]					szDeviceId = new byte[dpsdk_constant_value.DPSDK_CORE_DEV_ID_LEN];	// �豸ID
	public int						nAlarmOutChannelCount;												// ͨ������
	public AlarmOut_Channel_Info_t[]	pAlarmOutChannelnfo;													// ͨ����Ϣ

	public Get_AlarmOutChannel_Info_t(int nMaxAlarmOutChannelCount)
	{
		nAlarmOutChannelCount = nMaxAlarmOutChannelCount;
		pAlarmOutChannelnfo = new AlarmOut_Channel_Info_t[nAlarmOutChannelCount];	
		for(int i = 0;i < nAlarmOutChannelCount;i++)
		{
			pAlarmOutChannelnfo[i] = new AlarmOut_Channel_Info_t();
		}
	}
}
