package com.dh.DpsdkCore;

public class DPSDK_UserInfo_t {
	public int		iUserId;					// �û�id
	public int		iUserLevel;					// �û��ȼ�
	public boolean	iUserOperFlag;				// true����Աfalse��ҵ�ͻ�
	public byte[]	szCoding = new byte[dpsdk_constant_value.DPSDK_CORE_DGROUP_DGPCODE_LEN];        // �û�������֯
	public byte[]	szCodeName = new byte[dpsdk_constant_value.DPSDK_CORE_DGROUP_DEVICENAME_LEN];	// �û�������֯����
}
