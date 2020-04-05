package com.dh.DpsdkCore;

/** ����ͳ����Ϣ
@param   nChannelID					ͳ��ͨ����
@param   szRuleName					��������
@param   nStartTime					��ʼʱ��
@param   nEndTime					����ʱ��
@param   nEnteredSubTotal			��������С��
@param   nExitedSubtotal			��ȥ����С��
@param   nAvgInside					ƽ����������(��ȥ��ֵ)
@param   nMaxInside					���������
*/

public class Person_Count_Info_t
{		
	public int 		nChannelID;                         														// ͳ��ͨ����
	public byte[]  	szRuleName  = new byte[dpsdk_constant_value.DPSDK_CORE_IVS_EVENT_NAME_LEN];                 // ��������
	public int 		nStartTime;																					// ��ʼʱ��
	public int 		nEndTime;																					// ����ʱ��
	public int 		nEnteredSubTotal;																			// ��������С��
	public int 		nExitedSubtotal;																			// ��ȥ����С��
	public int 		nAvgInside;																					// ƽ����������(��ȥ��ֵ)
	public int 		nMaxInside;																					// ���������
};