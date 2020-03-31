package com.dh.DpsdkCore.TvWall;

public class TvWall_Task_Info_List_t {

	public int						nCount;										// ����ǽ��������
	public TVWall_Task_Info_t[]		pTvWallTaskInfo;							// ����ǽ������Ϣ
	
	public TvWall_Task_Info_List_t(int nMaxCount)
	{
		nCount = nMaxCount;
		pTvWallTaskInfo = new TVWall_Task_Info_t[nCount];
		for(int i = 0; i < nCount; i++)
		{
			pTvWallTaskInfo[i] = new TVWall_Task_Info_t();
		}
	}
}