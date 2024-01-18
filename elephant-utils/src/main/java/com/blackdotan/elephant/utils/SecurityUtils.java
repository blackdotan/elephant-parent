package com.blackdotan.elephant.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wmw on 5/30/16.
 */
public class SecurityUtils {


    //MD5加密
    public static String md5(String text) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //BASE64Encoder base64en = new BASE64Encoder();
            return Base64.encodeBase64String(text.getBytes("utf-8"));
            //return base64en.encode(md5.digest(text.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
