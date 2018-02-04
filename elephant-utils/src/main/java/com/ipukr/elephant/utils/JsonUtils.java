package com.ipukr.elephant.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by wmw on 16/10/21.
 */
public class JsonUtils {
    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DateFormat df = new SimpleDateFormat(DateUtils.STD_PATTERN);
        df.setTimeZone(DateUtils.tz);
        mapper.setDateFormat(df);
        mapper.setTimeZone(DateUtils.tz);
    }

    /**
     * Object对象 转成 字符串(Json)
     *
     * @param object 待转对象
     * @return 转化结果
     * */
    public static String parserObj2String(Object object) {
        String text = "";
        try{
            text = mapper.writeValueAsString(object);
        }catch (Exception e){
            logger.error("Object 2 Json text error");
            throw new RuntimeException("Object 2 Json text error");
        }
        return text;
    }

    /**
     * 字符串(Json) 转成 Object对象 <br>
     *
     * @param text 待处理文本
     * @param clazz 转化对象类型
     * @param <T> 元素T类型
     * @return 转化对象实例
     * @throws IOException IO异常
     * */
    public static <T> T parserString2Obj(String text, Class<T> clazz) throws IOException {
        T t = null;
        t =  mapper.readValue(text, clazz);
        return t;
    }

    /**
     * 文件(Json) 转成 Object对象 <br>
     *
     * @param file 待处理文件
     * @param clazz 转化对象类型
     * @param <T> 元素T类型
     * @return 转化对象实例
     * @throws IOException IO异常
     * */
    public static <T> T parserString2Obj(File file, Class<T> clazz) throws IOException {
        T t = null;
        t =  mapper.readValue(file, clazz);
        return t;
    }


    /**
     * 文本转Json对象 <br>
     *
     * @param text 待转文本
     * @param cClazz 集合类型
     * @param eClazz 元素T类型.class
     * @param <T> 元素T类型
     * @return T实例
     * @throws IOException IO异常
     */
    public static <T> T parserString2CollectionWithType(String text, java.lang.Class<? extends java.util.Collection> cClazz, java.lang.Class<?> eClazz) throws IOException {
        CollectionType type = mapper.getTypeFactory().constructCollectionType(cClazz, eClazz);
        return mapper.readValue(text, type);
    }

    /**
     * 文件转Json对象 <br>
     *
     * @param file 待转文件
     * @param cClazz 集合类型
     * @param eClazz 元素T类型.class
     * @param <T> 元素T类型
     * @return 元素T实例
     * @throws IOException IO异常
     */
    public static <T> T parserString2CollectionWithType(File file, java.lang.Class<? extends java.util.Collection> cClazz, java.lang.Class<?> eClazz) throws IOException {
        CollectionType type = mapper.getTypeFactory().constructCollectionType(cClazz, eClazz);
        return mapper.readValue(file, type);
    }

    /**
     * 验证Json是否有效
     *
     * @param text 待验证文本
     * @param clazz 待验证元素类型
     * @return 是否有效
     */
    public static boolean validate(String text, java.lang.Class<?> clazz) {
        try {
            return mapper.readValue(text, clazz) != null;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 验证Json是否有效
     *
     * @param text 待验证文本
     * @param cClazz 待验证集合类型
     * @param eClazz 待验证元素类型
     * @return 是否有效
     */
    public static boolean validate(String text, java.lang.Class<? extends java.util.Collection> cClazz, java.lang.Class<?> eClazz) {
        try {
            CollectionType type = mapper.getTypeFactory().constructCollectionType(cClazz, eClazz);
            return mapper.readValue(text, type) != null;
        } catch (IOException e) {
            return false;
        }
    }
}