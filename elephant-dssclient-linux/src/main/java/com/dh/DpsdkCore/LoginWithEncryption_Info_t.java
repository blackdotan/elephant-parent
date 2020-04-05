package com.dh.DpsdkCore;

/** 登录信息
@param   szIp						服务IP
@param   nPort						服务端口
@param   szUsername					登录账号
@param   szPassword					密码
@param   nProtocol					协议类型,参考类dpsdk_protocol_version_e定义
*/

public class LoginWithEncryption_Info_t
{		
	public byte[] 	szIp 		= new byte[dpsdk_constant_value.DPSDK_CORE_IP_LEN];								// 服务IP
	public int 		nPort;                         																// 服务端口
	public byte[]  	szUsername  = new byte[dpsdk_constant_value.DPSDK_CORE_USER_NAME_LEN];                   	// 登录账号
	public byte[]  	szPassword  = new byte[dpsdk_constant_value.DPSDK_CORE_PASSWORD_LEN];     					// 密码
	public int		nProtocol	= dpsdk_protocol_version_e.DPSDK_PROTOCOL_VERSION_II;							// 协议类型,参考类dpsdk_protocol_version_e定义
	public int		iType		= 2;																			// 登陆类型，1为PC客户端, 2为手机客户端
	public byte[]	szPhoneNum	= new byte[dpsdk_constant_value.DPSDK_CORE_IP_LEN];								// 手机号
	public byte[]	szEncryptedPassword	= new byte[dpsdk_constant_value.DPSDK_CORE_CHAR_LEN_256];				// 认证登录加密字符串
	public byte[]	szRealm		= new byte[dpsdk_constant_value.DPSDK_CORE_CHAR_LEN_64];						// 服务返回的域
	public byte[]	szNonce		= new byte[dpsdk_constant_value.DPSDK_CORE_CHAR_LEN_64];						// 服务返回的随机码
};
