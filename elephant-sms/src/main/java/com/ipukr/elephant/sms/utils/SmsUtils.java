package com.ipukr.elephant.sms.utils;

import java.util.Random;

/**
 * Created by wmw on 12/28/16.
 */
public class SmsUtils {

    public static String randomCode(){
        return randomCode(4);
    }

    public static String randomCode(int length){
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<length; i++){
            buffer.append(random.nextInt(10));
        }
        return buffer.toString();
    }
}
