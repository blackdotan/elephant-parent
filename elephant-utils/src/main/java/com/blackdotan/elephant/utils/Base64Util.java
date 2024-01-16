package com.blackdotan.elephant.utils;

import org.apache.commons.codec.binary.Base64;
import java.io.UnsupportedEncodingException;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/7/15.
 */
public class Base64Util {

	// 字符串编码
	private static final String UTF_8 = "UTF-8";

	/**
	 * 加密字符串
	 * @param inputData
	 * @return
	 */
	public static String decodeData(String inputData) throws UnsupportedEncodingException {
		if (null == inputData) {
			return null;
		}
		return new String(Base64.decodeBase64(inputData.getBytes(UTF_8)), UTF_8);
	}

	/**
	 * 解密加密后的字符串
	 * @param inputData
	 * @return
	 */
	public static String encodeData(String inputData) throws UnsupportedEncodingException {
		if (null == inputData) {
			return null;
		}
		return new String(Base64.encodeBase64(inputData.getBytes(UTF_8)), UTF_8);
	}

}
