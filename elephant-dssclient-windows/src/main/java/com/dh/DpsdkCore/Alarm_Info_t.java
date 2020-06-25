package com.dh.DpsdkCore;

public class Alarm_Info_t {
	public int							nCount;										// ����¼����
	public int						    nRetCount;									// ʵ�ʷ��ظ���
	public Single_Alarm_Info_t[]		pAlarmInfo;									// ������Ϣ
	
	public Alarm_Info_t(int nMaxCount)
	{
		nRetCount = 0;
		nCount = nMaxCount;
		pAlarmInfo = new Single_Alarm_Info_t[nCount];
		for(int i = 0; i < nCount; i++)
		{
			pAlarmInfo[i] = new Single_Alarm_Info_t();
		}
	}
}
