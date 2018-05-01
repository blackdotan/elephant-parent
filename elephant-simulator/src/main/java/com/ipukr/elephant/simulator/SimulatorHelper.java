package com.ipukr.elephant.simulator;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.text.TextProducer;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/4/30.
 */
public class SimulatorHelper {

    private static Fairy fairy = Fairy.create();

    /**
     * 随机用户
     * @return
     */
    public static Person person() {
        return fairy.person();
    }

    /**
     * 随机文本
     * @return
     */
    public static TextProducer text() {
        return fairy.textProducer();
    }

    /**
     * 随机头像
     * @param email
     * @return
     */
    public static String portrait(String email) {
        return portrait(email, 50);
    }


    /**
     * 随机头像
     * http://www.liuhaihua.cn/archives/287321.html
     *
     * @param email
     * @param sizeInPixels
     * @return
     */
    public static String portrait(String email, int sizeInPixels) {
        return "http://www.gravatar.com/avatar/"
                .concat(md5Hex(email))
                .concat("?s=200&d=")
//                .concat(random("mm", "identicon", "wavatar", "monsterid"));
                .concat(random("identicon", "monsterid", "robohash"));
    }

    /**
     * 随机整形
     * @param flooring
     * @param ceiling
     * @return
     */
    public static Integer random(Integer flooring, Integer ceiling) {
        return random(flooring, ceiling, Integer.MAX_VALUE);
    }

    private static String[] mobiles = new String[] {"134", "135", "136", "137", "138", "139", "150", "151", "152", "157", "158", "159", "130", "131", "132", "155", "156", "133", "153"};


    /**
     * 随机手机号码
     * @return
     */
    public static String mobile() {
        return mobiles[random(0, mobiles.length)].concat(random(10000000, 99999999).toString());
    }

    public static boolean random() {
        return new Random().nextBoolean();
    }

    /**
     * 随机征信
     * @param start
     * @param end
     * @param exclude
     * @return
     */
    public static Integer random(int start, int end, int... exclude) {
        int random = start + new Random().nextInt(end - start + 1 - exclude.length);
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random ++;
        }
        return random;
    }

    /**
     * 随机字符
     * @param args
     * @return
     */
    public static String random(String... args) {
        int i = new Random().nextInt(args.length);
        return args[i];
    }

    /**
     * 随机字符
     * @param arr
     * @return
     */
    public static <T> T random(List<T> arr) {
        int i = new Random().nextInt(arr.size());
        return arr.get(i);
    }

    public static <T> List<T> random(List<T> arr, int n) {
        if(arr.size() < n) {
            return arr;
        } else {
            List<T> _arr = new ArrayList<>();
            Collections.copy(arr, _arr);
            List<T> narr = new ArrayList<>();
            int size = arr.size();
            for (int i = 0; i < n; i++) {
                narr.add(_arr.remove(new Random().nextInt(size)));
                size -- ;
            }
            return narr;
        }
    }


    /**
     * @param array
     * @return
     */
    private static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }

    /**
     * md5加密
     * @param message
     * @return
     */
    private static String md5Hex (String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex (md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException e) {

        } catch (UnsupportedEncodingException e) {

        }
        return null;
    }



}
