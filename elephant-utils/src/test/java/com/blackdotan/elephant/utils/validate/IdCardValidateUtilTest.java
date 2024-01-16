package com.blackdotan.elephant.utils.validate;

import org.junit.Test;

public class IdCardValidateUtilTest {
    @Test
    public void testValidate() {
        String idcard15 = "130321860311519";
        String idcard18 = "210102198617083732";//
        String idcard = "350902199104100011";
        // 自己身份证测试
        System.out.println(IdCardValidateUtil.isValidatedAllIdcard(idcard));
        // 15位身份证
        System.out.println(IdCardValidateUtil.isValidatedAllIdcard(idcard15));
        // 18位身份证
        System.out.println(IdCardValidateUtil.isValidatedAllIdcard(idcard18));
        // 15位身份证转18位身份证
        //System.out.println(convertIdcarBy15bit(idcard15));
    }
}
