package com.dh.DpsdkCore;

//??????????????
public class AlarmIn_Channel_Info_t {
	public byte[] szId = new byte[dpsdk_constant_value.DPSDK_CORE_DEV_ID_LEN];					// ???ID:?豸ID+?????
	public byte[] szName = new byte[dpsdk_constant_value.DPSDK_CORE_DGROUP_DEVICENAME_LEN];	// ????
	public	long	                            nRight;                                     // ??????
	public	int                                 nChnlType;                                  // ???????
	public	int                                 nStatus;

}
