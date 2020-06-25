package com.dh.DpsdkCore;

public class dpsdk_AHostDefenceStatus_t {
	public byte[]	szNodeID = new byte[dpsdk_constant_value.DPSDK_CORE_DEV_ID_LEN];// �����ڵ�ID
	public int  	nAlarm;															// 0��ʾδ���� 1104��ʾ���� 1105��ʾ�� 1106��ʾ��
	public int  	nUndefendAlarm;													// 0��ʾû��δ�������� 83��ʾδ��������������24Сʱ��������ֲ���������δ����������������� ������Ҫ����һ�£�
	public boolean	bByPass;														// true=��·, false=δ��·
	public boolean	bDefend;														// true=����, false=����
}
