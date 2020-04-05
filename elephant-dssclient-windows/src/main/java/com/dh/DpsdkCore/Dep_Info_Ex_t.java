package com.dh.DpsdkCore;

/** ��֯��Ϣ(��չ)
@param   szCoding		�ڵ�code
@param   szDepName		�ڵ�����
@param   szModifyTime	�ڵ��޸�ʱ��
@param   szSN			Ψһ��ʶ��	
@param   szMemo			��ע��Ϣ
@param   nDepType		��֯�ڵ�����	
@param   nDepSort		��֯����
@param   nChargebooth	�շ�ͤ��־		
@param   nDepExtType	��֯�ڵ���չ����	
*/

public class Dep_Info_Ex_t 
{	
	public byte[]	szCoding		= new byte[dpsdk_constant_value.DPSDK_CORE_DGROUP_DGPCODE_LEN];			// �ڵ�code
	public byte[]	szDepName		= new byte[dpsdk_constant_value.DPSDK_CORE_DGROUP_DGPNAME_LEN];			// �ڵ�����
	public byte[]	szModifyTime	= new byte[dpsdk_constant_value.DPSDK_CORE_TIME_LEN];					// �ڵ��޸�ʱ��
	public byte[]	szSN			= new byte[dpsdk_constant_value.DPSDK_CORE_ORG_SN_LEN];					// Ψһ��ʶ��
	public byte[]	szMemo			= new byte[dpsdk_constant_value.DPSDK_CORE_MEM_LEN];					// ��ע��Ϣ -->������Ӫƽ̨
	public int		nDepType;																				// ��֯�ڵ����� -->������Ӫƽ̨
	public int		nDepSort;																				// ��֯����
	public int		nChargebooth;																			// �շ�ͤ��־
	public int		nDepExtType;																			// ��֯�ڵ���չ����
}; 