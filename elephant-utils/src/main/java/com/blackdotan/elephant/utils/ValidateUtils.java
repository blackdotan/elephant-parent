package com.blackdotan.elephant.utils;

import com.blackdotan.elephant.utils.validate.IdCardValidateUtil;

import java.util.regex.Pattern;

/**
 * Created by wmw on 16/10/27.
 */
public class ValidateUtils {
    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z0-9]{5,20}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{5,20}$";

    /**
     * 正则表达式：验证手机号
     */
    @Deprecated
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE_V2 = "1[3456789]\\d{9}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：中文名
     */
    public static final String REGEX_CHINESE_NAME = "^[\u4e00-\u9fa5],{2,}$";

    /**
     * 正则表达式：验证身份证
     */
    @Deprecated
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD_V2 = "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)|(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 校验用户名
     *
     * @param username 用户名
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     *
     * @param password 密码
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     *
     * @param mobile 手机号
     * @return 校验通过返回true，否则返回false
     */
    @Deprecated
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验手机号
     * @param mobile 手机号
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobileV2(String mobile) {
        return Pattern.matches(REGEX_MOBILE_V2, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email 邮件
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }


    /**
     * 校验汉字
     *
     * @param chinese 中文
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }


    /**
     * 中文名称
     * @param chineseName 中文名称
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChineseName(String chineseName) {
        return Pattern.matches(REGEX_CHINESE_NAME, chineseName);
    }

    /**
     * 校验身份证
     *
     * @param idCard 身份证
     * @return 校验通过返回true，否则返回false
     */
    @Deprecated
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验身份证
     *
     * @param idCard 身份证
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCardV2(String idCard) {
        return Pattern.matches(REGEX_ID_CARD_V2, idCard);
    }


    public static boolean isIDCardV3(String idCard) {
        return IdCardValidateUtil.isValidatedAllIdcard(idCard);
    }

    /**
     * 校验URL
     *
     * @param url url地址
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 校验IP地址
     *
     * @param ipAddr IP地址
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

}
