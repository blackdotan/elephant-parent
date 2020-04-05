package com.dh.DpsdkCore;

/** ��¼Ƭͷ
@param   m_deviceId					�豸ID
@param   m_password					��������
@param   m_caseId					1.�������
@param   m_trialSeq					2.�������/��Ѷ���
@param   m_caseUnderTaker			3.�참��Ա
@param   m_caseDep					4.�참��λ
@param   m_caseReason				5.��������
@param   m_caseReferPerson			6.�永��Ա
@param   m_caseRemark				7.����ע
@param   m_caseRecordName			8.¼������
@param   m_RecordNum				9.���̱��
@param   m_recordPerson				10.��¼��
@param   m_dataCheckOsdEn			11.���̿�¼����У������/����ʹ��
@param   m_AttachFileEn				12.�����ļ�ʹ��
@param   m_multiBurnerDataCheck		13.�����һ����У��ʹ��
@param   m_multiBurnerDataCheckSpeed	14.У���ٶ�У���ٶ� 0 ���٣�ͷβ����У�飩,1 �������������У�飩,2 ���� ��ȫ������У�飩,Ĭ��0
*/

public class DevBurnerInfoHeader_t
{
	public byte[]	m_deviceId			=new byte[dpsdk_constant_value.DPSDK_CORE_CHAR_LEN_32];		// �豸ID
	public byte[]	m_password			=new byte[dpsdk_constant_value.DPSDK_CORE_CHAR_LEN_64];		// ��������
	public byte[]	m_caseId			=new byte[dpsdk_constant_value.DPSDK_CORE_CHAR_LEN_64];		// 1.�������
	public byte[]	m_trialSeq			=new byte[dpsdk_constant_value.DPSDK_CORE_CHAR_LEN_256];	// 2.�������/��Ѷ���
	public byte[]	m_caseUnderTaker	=new byte[dpsdk_constant_value.DPSDK_CORE_CHAR_LEN_256];	// 3.�참��Ա
	public byte[]	m_caseDep			=new byte[dpsdk_constant_value.DPSDK_CORE_CHAR_LEN_256];	// 4.�참��λ	
	public byte[]	m_caseReason		=new byte[dpsdk_constant_value.DPSDK_CORE_CHAR_LEN_256];	// 5.��������
	public byte[]	m_caseReferPerson	=new byte[dpsdk_constant_value.DPSDK_CORE_CHAR_LEN_256];	// 6.�永��Ա
	public byte[]	m_caseRemark		=new byte[dpsdk_constant_value.DPSDK_CORE_CHAR_LEN_256];	// 7.����ע
	public byte[]	m_caseRecordName	=new byte[dpsdk_constant_value.DPSDK_CORE_CHAR_LEN_256];	// 8.¼������
	public byte[]	m_RecordNum			=new byte[dpsdk_constant_value.DPSDK_CORE_CHAR_LEN_64];		// 9.���̱��
	public byte[]	m_recordPerson		=new byte[dpsdk_constant_value.DPSDK_CORE_CHAR_LEN_256];	// 10.��¼��
	public boolean	m_dataCheckOsdEn;																// 11.���̿�¼����У������/����ʹ��
	public boolean	m_AttachFileEn;																	// 12.�����ļ�ʹ��
	public boolean	m_multiBurnerDataCheck;															// 13.�����һ����У��ʹ��
	public int		m_multiBurnerDataCheckSpeed;													// 14.У���ٶ�У���ٶ� 0 ���٣�ͷβ����У�飩,1 �������������У�飩,2 ���� ��ȫ������У�飩,Ĭ��0
};