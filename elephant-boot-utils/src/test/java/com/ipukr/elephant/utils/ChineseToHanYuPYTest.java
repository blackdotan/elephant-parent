package com.ipukr.elephant.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p/>
 *         Created by ryan on 2017/10/31.
 */
public class ChineseToHanYuPYTest {
    private static Map<String, List<String>> pinyinMap = new HashMap<String, List<String>>();
    private static long count = 0;

    @Test
    public void test() throws FileNotFoundException {
        String resource = this.getClass().getResource("/").getPath().concat("polyphone.txt");
        System.out.println(resource);
        String str = "扁担";

        InputStream ins = new FileInputStream(resource);
        initPinyin(ins);
        String py = convertChineseToPinyin(str);
        System.out.println(str+" = "+py);
    }

    /**
     * 将某个字符串的首字母 大写
     * @param str
     * @return
     */
    public static String convertInitialToUpperCase(String str){
        if(str==null){
            return null;
        }
        StringBuffer sb = new StringBuffer();
        char[] arr = str.toCharArray();
        for(int i=0;i<arr.length;i++){
            char ch = arr[i];
            if(i==0){
                sb.append(String.valueOf(ch).toUpperCase());
            }else{
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /**
     * 汉字转拼音 最大匹配优先
     * @param chinese
     * @return
     */
    private static String convertChineseToPinyin(String chinese) {
        return null;
    }

    /**
     * 初始化 所有的多音字词组
     *
     * @param ins
     */
    public static void initPinyin(InputStream ins) {
        // 读取多音字的全部拼音表;
        BufferedReader br = new BufferedReader(new InputStreamReader(ins));

        String s = null;
        try {
            while ((s = br.readLine()) != null) {
                if (s != null) {
                    String[] arr = s.split("#");
                    String pinyin = arr[0];
                    String chinese = arr[1];
                    if(chinese!=null){
                        String[] strs = chinese.split(" ");
                        List<String> list = Arrays.asList(strs);
                        pinyinMap.put(pinyin, list);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
