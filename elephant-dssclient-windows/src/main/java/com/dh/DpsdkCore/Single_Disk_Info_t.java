package com.dh.DpsdkCore;

/** ����������Ϣ
@param   nDiskId					Ӳ��ID����0��ʼ��
@param   uVolume					Ӳ������
@param   uFreeSpace					ʣ������
@param   diskState					����λ��ֵ��ʾӲ�����ͣ�����Ϊ��0 ��д������ 1 ֻ�������� 2 ������������ý�������� 3 ���������� 4 ����������������λ��ֵ��ʾӲ�̵�״̬��0-����,1-�,2-����
@param   diskNum					Ӳ�̺�
@param   subareaNum					������
@param   signal						��ʶ�� 0���� 1 Զ��
*/

public class Single_Disk_Info_t
{
	public int				nDiskId;						//Ӳ��ID����0��ʼ��
	public int				uVolume;						//Ӳ������
	public int				uFreeSpace;						//ʣ������
	public byte				diskState;						//����λ��ֵ��ʾӲ�����ͣ�����Ϊ��0 ��д������ 1 ֻ�������� 2 ������������ý�������� 3 ���������� 4 ����������������λ��ֵ��ʾӲ�̵�״̬��0-����,1-�,2-����
	public byte				diskNum;						//Ӳ�̺�
	public byte				subareaNum;						//������
	public byte				signal;							//��ʶ�� 0���� 1 Զ��
};