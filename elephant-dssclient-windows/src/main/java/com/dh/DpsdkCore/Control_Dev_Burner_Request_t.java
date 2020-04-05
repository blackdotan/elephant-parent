package com.dh.DpsdkCore;

/** ��ȡ��¼ʵʱ״̬��Ϣ����
@param   deviceId					�豸ID
@param   cmd						��������
@param   channelMask				ͨ������    ��1��ͨ��Ϊ1����2��ͨ���ǣ�1<<1;��3����1<<2
@param   burnerMask					��¼������  1��ʾ����1��2����2��3˫����
@param   emMode						��¼ģʽ    0-BURN_MODE_SYNC,1-BURN_MODE_TURN,2-BURN_MODE_CYCLE
@param   emPack						��¼����ʽ  0-DHAV,1-BURN_PACK_PS...
*/

public class Control_Dev_Burner_Request_t
{
	public byte[] 	deviceId 	= new byte[dpsdk_constant_value.DPSDK_CORE_DEV_ID_LEN];
	public int		cmd;
	public int 		channelMask;
	public int 		burnerMask;
	public int 		emMode;
	public int 		emPack;
};