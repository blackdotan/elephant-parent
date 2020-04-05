package com.dh.DpsdkCore;

public class dpsdk_dev_type_e {
	public static final int     DEV_TYPE_ENC_BEGIN			= 0;		// �����豸
	public static final int     DEV_TYPE_DVR				= DEV_TYPE_ENC_BEGIN + 1;			// DVR
	public static final int     DEV_TYPE_IPC				= DEV_TYPE_ENC_BEGIN + 2;			// IPC
	public static final int     DEV_TYPE_NVS				= DEV_TYPE_ENC_BEGIN + 3;			// NVS
	public static final int     DEV_TYPE_MCD				= DEV_TYPE_ENC_BEGIN + 4;			// MCD
	public static final int		DEV_TYPE_MDVR				= DEV_TYPE_ENC_BEGIN + 5;			// MDVR
	public static final int		DEV_TYPE_NVR				= DEV_TYPE_ENC_BEGIN + 6;			// NVR
	public static final int		DEV_TYPE_SVR				= DEV_TYPE_ENC_BEGIN + 7;			// SVR
	public static final int		DEV_TYPE_PCNVR				= DEV_TYPE_ENC_BEGIN + 8;			// PCNVR��PSS�Դ���һ��С�ͷ���
	public static final int		DEV_TYPE_PVR				= DEV_TYPE_ENC_BEGIN + 9;			// PVR
	public static final int		DEV_TYPE_EVS				= DEV_TYPE_ENC_BEGIN + 10;			// EVS
	public static final int		DEV_TYPE_MPGS				= DEV_TYPE_ENC_BEGIN + 11;			// MPGS
	public static final int		DEV_TYPE_SMART_IPC			= DEV_TYPE_ENC_BEGIN + 12;			// SMART_IPC
	public static final int		DEV_TYPE_SMART_TINGSHEN		= DEV_TYPE_ENC_BEGIN + 13;			// ͥ������
	public static final int		DEV_TYPE_SMART_NVR			= DEV_TYPE_ENC_BEGIN + 14;			// SMART_NVR
	public static final int		DEV_TYPE_PRC				= DEV_TYPE_ENC_BEGIN + 15;			// ������
	public static final int		DEV_TYPE_JT808				= DEV_TYPE_ENC_BEGIN + 18;			// ����JT808

	public static final int		DEV_TYPE_ENC_END			= 99;

	public static final int		DEV_TYPE_TVWALL_BEGIN		= 100;
	public static final int		DEV_TYPE_BIGSCREEN			= DEV_TYPE_TVWALL_BEGIN + 1;		// ����
	public static final int		DEV_TYPE_TVWALL_END			= 199;

	public static final int		DEV_TYPE_DEC_BEGIN			= 200;		// �����豸
	public static final int		DEV_TYPE_NVD				= DEV_TYPE_DEC_BEGIN + 1;			// NVD
	public static final int		DEV_TYPE_SNVD				= DEV_TYPE_DEC_BEGIN + 2;			// SNVD
	public static final int		DEV_TYPE_UDS				= DEV_TYPE_DEC_BEGIN + 5;			// UDS
	public static final int		DEV_TYPE_DEC_END			=299;

	public static final int		DEV_TYPE_MATRIX_BEGIN		= 300;		// �����豸
	public static final int		DEV_MATRIX_M60				= DEV_TYPE_MATRIX_BEGIN	+ 1;		// M60
	public static final int		DEV_MATRIX_NVR6000			= DEV_TYPE_MATRIX_BEGIN + 2;		// NVR6000
	public static final int		DEV_TYPE_MATRIX_END			= 399;

	public static final int		DEV_TYPE_IVS_BEGIN			= 400;		// �����豸
	public static final int		DEV_TYPE_ISD				= DEV_TYPE_IVS_BEGIN + 1;			// ISD ������
	public static final int		DEV_TYPE_IVS_B				= DEV_TYPE_IVS_BEGIN + 2;			// IVS-B ��Ϊ��������
	public static final int		DEV_TYPE_IVS_V				= DEV_TYPE_IVS_BEGIN + 3;			// IVS-V ��Ƶ������Ϸ���
	public static final int		DEV_TYPE_IVS_FR				= DEV_TYPE_IVS_BEGIN + 4;			// IVS-FR ����ʶ�����
	public static final int		DEV_TYPE_IVS_PC				= DEV_TYPE_IVS_BEGIN + 5;			// IVS-PC ������ͳ�Ʒ���
	public static final int		DEV_TYPE_IVS_M				= DEV_TYPE_IVS_BEGIN + 6;			// IVS_M ���Ӹ������ܺ�
	public static final int		DEV_TYPE_IVS_PC_BOX			= DEV_TYPE_IVS_BEGIN + 7;			// IVS-PC ���ܺ� 
	public static final int		DEV_TYPE_IVS_B_BOX			= DEV_TYPE_IVS_BEGIN + 8;			// IVS-B ���ܺ�
	public static final int		DEV_TYPE_IVS_M_BOX			= DEV_TYPE_IVS_BEGIN + 9;			// IVS-M ����
	public static final int		DEV_TYPE_IVS_PRC			= DEV_TYPE_IVS_BEGIN + 10;			// ������
	public static final int		DEV_TYPE_IVS_END			= 499;

	public static final int		DEV_TYPE_BAYONET_BEGIN		= 500;		// -C����豸
	public static final int		DEV_TYPE_CAPTURE			= DEV_TYPE_BAYONET_BEGIN + 1;		// �����豸
	public static final int		DEV_TYPE_SPEED				= DEV_TYPE_BAYONET_BEGIN + 2;		// �����豸
	public static final int		DEV_TYPE_TRAFFIC_LIGHT		= DEV_TYPE_BAYONET_BEGIN + 3;		// ������豸
	public static final int		DEV_TYPE_INCORPORATE		= DEV_TYPE_BAYONET_BEGIN + 4;		// һ�廯�豸
	public static final int		DEV_TYPE_PLATEDISTINGUISH	= DEV_TYPE_BAYONET_BEGIN + 5;		// ����ʶ���豸
	public static final int		DEV_TYPE_VIOLATESNAPPIC		= DEV_TYPE_BAYONET_BEGIN + 6;		// Υͣ����豸
	public static final int		DEV_TYPE_PARKINGSTATUSDEV	= DEV_TYPE_BAYONET_BEGIN + 7;		// ��λ����豸
	public static final int		DEV_TYPE_ENTRANCE			= DEV_TYPE_BAYONET_BEGIN + 8;		// ������豸
	public static final int		DEV_TYPE_VIOLATESNAPBALL	= DEV_TYPE_BAYONET_BEGIN + 9;		// Υͣץ�����
	public static final int		DEV_TYPE_THIRDBAYONET		= DEV_TYPE_BAYONET_BEGIN + 10;		// �����������豸
	public static final int		DEV_TYPE_ULTRASONIC			= DEV_TYPE_BAYONET_BEGIN + 11;		// ��������λ�����
	public static final int		DEV_TYPE_FACE_CAPTURE		= DEV_TYPE_BAYONET_BEGIN + 12;		// ����ץ���豸
	public static final int		DEV_TYPE_ITC_SMART_NVR		= DEV_TYPE_BAYONET_BEGIN + 13;		// ��������NVR�豸
	public static final int		DEV_TYPE_BAYONET_END		= 599;

	public static final int		DEV_TYPE_ALARM_BEGIN		= 600;		// �����豸
	public static final int		DEV_TYPE_ALARMHOST			= DEV_TYPE_ALARM_BEGIN + 1;			// ��������
	public static final int		DEV_TYPE_ALARM_END			= 699;

	public static final int		DEV_TYPE_DOORCTRL_BEGIN		= 700;
	public static final int		DEV_TYPE_DOORCTRL_DOOR		= DEV_TYPE_DOORCTRL_BEGIN + 1;		// �Ž�
	public static final int		DEV_TYPE_DOORCTRL_END		=799;

	public static final int		DEV_TYPE_PE_BEGIN			= 800;
	public static final int		DEV_TYPE_PE_PE				= DEV_TYPE_PE_BEGIN + 1;			// ����
	public static final int		DEV_TYPE_PE_AE6016			= DEV_TYPE_PE_BEGIN + 2;			// AE6016�豸
	public static final int		DEV_TYPE_PE_NVS				= DEV_TYPE_PE_BEGIN + 3;			// ���������ܵ�NVS�豸
	public static final int		DEV_TYPE_PE_END				=899;

	public static final int		DEV_TYPE_VOICE_BEGIN		= 900;		// ip�Խ�
	public static final int		DEV_TYPE_VOICE_MIKE			= DEV_TYPE_VOICE_BEGIN + 1;
	public static final int		DEV_TYPE_VOICE_NET			= DEV_TYPE_VOICE_BEGIN + 2;
	public static final int		DEV_TYPE_VOICE_END			=999;

	public static final int		DEV_TYPE_IP_BEGIN			= 1000;		// IP�豸��ͨ�����������豸��
	public static final int		DEV_TYPE_IP_SCNNER			= DEV_TYPE_IP_BEGIN + 1;			// ɨ��ǹ
	public static final int		DEV_TYPE_IP_SWEEP			= DEV_TYPE_IP_BEGIN + 2;			// �ذ�
	public static final int		DEV_TYPE_IP_POWERCONTROL	= DEV_TYPE_IP_BEGIN + 3;			// ��Դ������
	public static final int		DEV_TYPE_IP_END				= 1099;

	public static final int		DEV_TYPE_MULTIFUNALARM_BEGIN= 1100;		// �๦�ܱ�������
	public static final int		DEV_TYPE_VEDIO_ALARMHOST	= DEV_TYPE_MULTIFUNALARM_BEGIN + 1;	// ��Ƶ��������
	public static final int		DEV_TYPE_MULTIFUNALARM_END	= 1199;

	public static final int		DEV_TYPE_SLUICE_BEGIN		= 1200;
	public static final int		DEV_TYPE_SLUICE_DEV			= DEV_TYPE_SLUICE_BEGIN + 1;		// ����ڵ�բ�豸
	public static final int		DEV_TYPE_SLUICE_PARKING		= DEV_TYPE_SLUICE_BEGIN + 2;		// ͣ������բ�豸
	public static final int		DEV_TYPE_SLUICE_STOPBUFFER	= DEV_TYPE_SLUICE_BEGIN + 3;		// ��Ƶ������
	public static final int		DEV_TYPE_SLUICE_END			= 1299;

	public static final int		DEV_TYPE_ELECTRIC_BEGIN		= 1300;
	public static final int		DEV_TYPE_ELECTRIC_DEV		= DEV_TYPE_ELECTRIC_BEGIN + 1;		// �����豸
	public static final int		DEV_TYPE_ELECTRIC_END		= 1399;

	public static final int		DEV_TYPE_LED_BEGIN			= 1400;
	public static final int		DEV_TYPE_LED_DEV			= DEV_TYPE_LED_BEGIN + 1;			// LED���豸
	public static final int		DEV_TYPE_LED_END			= 1499;

	public static final int		DEV_TYPE_VIBRATIONFIBER_BEGIN	= 1500;		
	public static final int		DEV_TYPE_VIBRATIONFIBER_DEV	= DEV_TYPE_VIBRATIONFIBER_BEGIN + 1;// �𶯹����豸 
	public static final int		DEV_TYPE_VIBRATIONFIBER_END = 1599;

	public static final int		DEV_TYPE_PATROL_BEGIN		= 1600;		
	public static final int		DEV_TYPE_PATROL_DEV			= DEV_TYPE_PATROL_BEGIN + 1;		// Ѳ�����豸
	public static final int		DEV_TYPE_PATROL_SPOT		= DEV_TYPE_PATROL_BEGIN + 2;		// Ѳ�����豸
	public static final int		DEV_TYPE_PATROL_END			= 1699;

	public static final int		DEV_TYPE_SENTRY_BOX_BEGIN	= 1700;		
	public static final int		DEV_TYPE_SENTRY_BOX_DEV		= DEV_TYPE_SENTRY_BOX_BEGIN + 1;	// ��λ���豸
	public static final int		DEV_TYPE_SENTRY_BOX_END		= 1799;

	public static final int		DEV_TYPE_COURT_BEGIN		= 1800;		
	public static final int		DEV_TYPE_COURT_DEV			= DEV_TYPE_COURT_BEGIN + 1;			// ͥ���豸
	public static final int		DEV_TYPE_COURT_END			= 1899;

	public static final int		DEV_TYPE_VIDEO_TALK_BEGIN	= 1900;
	public static final int		DEV_TYPE_VIDEO_TALK_VTNC	= DEV_TYPE_VIDEO_TALK_BEGIN + 1; 
	public static final int		DEV_TYPE_VIDEO_TALK_VTO		= DEV_TYPE_VIDEO_TALK_BEGIN + 2;  
	public static final int		DEV_TYPE_VIDEO_TALK_VTH		= DEV_TYPE_VIDEO_TALK_BEGIN + 3;  
	public static final int		DEV_TYPE_VIDEO_TALK_END		= 1999;

	public static final int		DEV_TYPE_BROADCAST_BEGIN	= 2000;
	public static final int		DEV_TYPE_BROADCAST_ITC_T6700R = DEV_TYPE_BROADCAST_BEGIN + 1;	// ITC_T6700R�㲥�豸
	public static final int		DEV_TYPE_BROADCAST_END		= 2099;

	public static final int		DEV_TYPE_VIDEO_RECORD_SERVER_BEGIN = 2100;
	public static final int		DEV_TYPE_VIDEO_RECORD_SERVER_BNVR	= DEV_TYPE_VIDEO_RECORD_SERVER_BEGIN + 1; // BNVR�豸
	public static final int		DEV_TYPE_VIDEO_RECORD_SERVER_OE	= DEV_TYPE_VIDEO_RECORD_SERVER_BEGIN + 2; // �����豸(operation equipment)
	public static final int		DEV_TYPE_VIDEO_RECORD_SERVER_END= 2199;

}
