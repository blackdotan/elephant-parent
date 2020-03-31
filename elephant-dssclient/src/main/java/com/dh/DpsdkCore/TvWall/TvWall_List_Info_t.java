package com.dh.DpsdkCore.TvWall;

public class TvWall_List_Info_t {
	
	public int							nCount;										// ����ǽ����
	public TvWall_Info_t[]		        pTvWallInfo;							    // ����ǽ��Ϣ
	
	public TvWall_List_Info_t(int nMaxCount)
	{
		nCount = nMaxCount;
		pTvWallInfo = new TvWall_Info_t[nCount];
		for(int i = 0; i < nCount; i++)
		{
			pTvWallInfo[i] = new TvWall_Info_t();
		}
	}
}
