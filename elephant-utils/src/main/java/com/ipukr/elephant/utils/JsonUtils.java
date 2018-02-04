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
     * @param text
     * @param clazz
     * @return
     * @throws IOException
     * */
    public static <T> T parserString2Obj(String text,Class<T> clazz) throws IOException {
        T t = null;
        t =  mapper.readValue(text, clazz);
        return t;
    }

    /**
     * 文件(Json) 转成 Object对象 <br>
     *
     * @param file
     * @param clazz
     * @return
     * @throws IOException
     * */
    public static <T> T parserString2Obj(File file, Class<T> clazz) throws IOException {
        T t = null;
        t =  mapper.readValue(file, clazz);
        return t;
    }


    /**
     * Map对象add对象
     * */
    public static Map enter(Map<Object,Object> map, Object object, String alia){
        map.put(alia,object);
        return map;
    }


    /**
     * 文本转Json对象 <br>
     *
     * @param text 待转文本
     * @param cClazz 集合类型
     * @param eClazz 元素类型
     * @param <T>
     * @return
     * @throws IOException
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
     * @param eClazz 元素类型
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T parserString2CollectionWithType(File file, java.lang.Class<? extends java.util.Collection> cClazz, java.lang.Class<?> eClazz) throws IOException {
        CollectionType type = mapper.getTypeFactory().constructCollectionType(cClazz, eClazz);
        return mapper.readValue(file, type);
    }

    /**
     * 验证Json是否有效
     *
     * @param text
     * @param clazz
     * @return
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
     * @param text
     * @param cClazz
     * @param eClazz
     * @return
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