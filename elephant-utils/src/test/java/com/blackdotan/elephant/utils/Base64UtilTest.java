package com.blackdotan.elephant.utils;

import org.codehaus.plexus.util.Base64;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/7/15.
 */
public class Base64UtilTest {
	@Test
	public void test() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		String text = "闽022015002666";
		// WfEtnX/DX4pJJaSFYNAHRg==
		System.out.println(Base64Util.encodeData(text));
		System.out.println(Base64Util.decodeData(Base64Util.encodeData(text)));
		System.out.println(SecurityUtils.md5(text));
		System.out.println(new Base64().encode(text.getBytes()));

		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] rst = md5.digest(text.getBytes("utf-8"));
		System.out.println(new String(rst));
	}


	@Test
	public void testBase() throws UnsupportedEncodingException {
		String[] texts = new String[]{
				"{\"scanType\": \"0\", \"sharingGymId\": \"4\", \"openDoorId\" : 101, \"direction\": \"1\"}",
				"{\"scanType\": \"0\", \"sharingGymId\": \"4\", \"openDoorId\" : 101, \"direction\": \"1\"}",
				"{\"scanType\": \"0\", \"sharingGymId\": \"4\", \"openDoorId\" : 102, \"direction\": \"0\"}",
				"{\"scanType\": \"0\", \"sharingGymId\": \"4\", \"openDoorId\" : 102, \"direction\": \"0\"}",
				"{\"scanType\": \"0\", \"sharingGymId\": \"4\", \"openDoorId\" : 102, \"direction\": \"0\"}",
				"{\"scanType\": \"0\", \"sharingGymId\": \"4\", \"openDoorId\" : 103, \"direction\": \"1\"}",
				"{\"scanType\": \"0\", \"sharingGymId\": \"4\", \"openDoorId\" : 103, \"direction\": \"0\"}"
		};
		for (String text : texts) {
			System.out.print(text);
			System.out.print(" ==> ");
			System.out.print(Base64Util.encodeData(text));
			System.out.println();
		}

	}

	@Test
	public void name() {
		String text = "闽022015001234";
		System.out.println(text);
		char[] chars = text.toCharArray();
		char val1 = chars[chars.length-1];
		char val2 = chars[chars.length-2];
		char val3 = chars[chars.length-3];
		char val4 = chars[chars.length-4];
		chars[chars.length-1] = val4;
		chars[chars.length-2] = val2;
		chars[chars.length-3] = val3;
		chars[chars.length-4] = val1;
		String result = new String(chars);
		System.out.println(result);
		char[] revs = result.toCharArray();
		char rev1 = revs[revs.length-1];
		char rev2 = revs[revs.length-2];
		char rev3 = revs[revs.length-3];
		char rev4 = revs[revs.length-4];
		revs[revs.length-1] = rev4;
		revs[revs.length-2] = rev2;
		revs[revs.length-3] = rev3;
		revs[revs.length-4] = rev1;
		System.out.println(new String(revs));
	}

}
