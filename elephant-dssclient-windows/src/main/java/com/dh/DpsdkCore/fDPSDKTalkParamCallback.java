package com.dh.DpsdkCore;

/** �����Խ���Ϣ���ص���������
@param	  IN	                                nPDLLHandle		    SDK���
@param	  IN									nTalkType			�Խ����� 	dpsdk_talk_type_e
@param	  IN	                                nAudioType			��Ƶ���� 	dpsdk_audio_type_e
@param	  IN	                                nAudioBit			λ��		dpsdk_talk_bits_e
@param	  IN	                                nSampleRate			������	Talk_Sample_Rate_e
@param	  IN	                                nTransMode			��������	dpsdk_trans_type_e
*/

public interface fDPSDKTalkParamCallback 
{
	public void invoke(int nPDLLHandle,
                       int nTalkType,
                       int nAudioType,
                       int nAudioBit,
                       int nSampleRate,
                       int nTransMode
    );
}
