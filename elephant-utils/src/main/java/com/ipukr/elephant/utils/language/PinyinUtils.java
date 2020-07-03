package com.ipukr.elephant.utils.language;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中文转拼音
 *
 * @author ryan.
 *
 * Created by ryan on 2017/10/31.
 */
public class PinyinUtils {

    private static Map<String, List<String>> polyphone = null;

    /**
     * text内容 转拼音
     *
     * @param text 待转字符串
     * @return 拼音结果
     * @throws Exception 异常
     */
    public static String pinyin(String text) throws Exception {
        if(polyphone == null) {
            // 初始化拼音对象
            synchronized(PinyinUtils.class){
                if (polyphone == null) {
                    polyphone = new HashMap<String, List<String>>();
                    InputStream ins = PinyinUtils.class.getResourceAsStream("/polyphone.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(ins));
                    String s = null;
                    try {
                        while ((s = br.readLine()) != null) {
                            if (s != null) {
                                String[] arr = s.split("#");
                                String pinyin = arr[0];
                                String chinese = arr[1];
                                if (chinese != null) {
                                    String[] strs = chinese.split(" ");
                                    List<String> list = Arrays.asList(strs);
                                    polyphone.put(pinyin, list);
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        StringBuffer pinyin = new StringBuffer();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        char[] arr = text.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (ch > 128) {
                // 非ASCII码
                // 取得当前汉字的所有全拼
                try {
                    String[] results = PinyinHelper.toHanyuPinyinStringArray(ch, defaultFormat);
                    if (results == null) {  //非中文
                        return "";
                    } else {
                        int len = results.length;
                        if (len == 1) {
                            // 不是多音字
                            String py = results[0];
                            if(py.contains("u:")){
                                //过滤 u:
                                py = py.replace("u:", "v");
                            }
                            pinyin.append(convertInitialToUpperCase(py));
                        }else if(results[0].equals(results[1])){
                            //非多音字 有多个音，取第一个
                            pinyin.append(convertInitialToUpperCase(results[0]));
                        }else {
                            // 多音字
                            int length = text.length();
                            boolean flag = false;
                            String s = null;
                            List<String> keyList =null;
                            for (int x = 0; x < len; x++) {
                                String py = results[x];
                                if(py.contains("u:")){  //过滤 u:
                                    py = py.replace("u:", "v");
                                }
                                keyList = polyphone.get(py);
                                if (i + 3 <= length) {   //后向匹配2个汉字  大西洋
                                    s = text.substring(i, i + 3);
                                    if (keyList != null && (keyList.contains(s))) {
                                        pinyin.append(convertInitialToUpperCase(py));
                                        flag = true;
                                        break;
                                    }
                                }

                                if (i + 2 <= length) {   //后向匹配 1个汉字  大西
                                    s = text.substring(i, i + 2);
                                    if (keyList != null && (keyList.contains(s))) {
                                        pinyin.append(convertInitialToUpperCase(py));
                                        flag = true;
                                        break;
                                    }
                                }

                                if ((i - 2 >= 0) && (i+1<=length)) {
                                    // 前向匹配2个汉字 龙固大
                                    s = text.substring(i - 2, i+1);
                                    if (keyList != null && (keyList.contains(s))) {
                                        pinyin.append(convertInitialToUpperCase(py));
                                        flag = true;
                                        break;
                                    }
                                }

                                if ((i - 1 >= 0) && (i+1<=length)) {  // 前向匹配1个汉字   固大
                                    s = text.substring(i - 1, i+1);
                                    if (keyList != null && (keyList.contains(s))) {
                                        pinyin.append(convertInitialToUpperCase(py));
                                        flag = true;
                                        break;
                                    }
                                }

                                if ((i - 1 >= 0) && (i+2<=length)) {  //前向1个，后向1个      固大西
                                    s = text.substring(i - 1, i+2);
                                    if (keyList != null && (keyList.contains(s))) {
                                        pinyin.append(convertInitialToUpperCase(py));
                                        flag = true;
                                        break;
                                    }
                                }
                            }

                            if (!flag) {
                                //都没有找到，匹配默认的 读音  大
                                s = String.valueOf(ch);
                                for (int x = 0; x < len; x++) {
                                    String py = results[x];
                                    if(py.contains("u:")){
                                        //过滤 u:
                                        py = py.replace("u:", "v");
                                    }
                                    keyList = polyphone.get(py);
                                    if (keyList != null && (keyList.contains(s))) {
                                        pinyin.append(convertInitialToUpperCase(py));//拼音首字母 大写
                                        break;
                                    }
                                }
                            }
                        }
                    }

                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyin.append(arr[i]);
            }
        }
        return pinyin.toString();
    }

    /**
     * 将某个字符串的首字母 大写
     *
     * @param text 待转字符串
     * @return 首字母转大写后字符串
     */
    private static String convertInitialToUpperCase(String text){
        char[] chs = text.toCharArray();
        chs[0] -= 32;
        return String.valueOf(chs);
    }

}
