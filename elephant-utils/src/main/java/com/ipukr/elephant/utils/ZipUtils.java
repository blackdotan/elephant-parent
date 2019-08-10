package com.ipukr.elephant.utils;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/4/15.
 */
public class ZipUtils {


    /**
     * 是否是压缩包格式
     * @param filename
     * @return
     */
    public static boolean isValidate(String filename) {
        return filename.matches("^.+\\.(zip|rar)$");
    }


    /**
     * @param ins
     * @return
     * @throws IOException
     */
    public static List<FileModel> unzip(InputStream ins) throws IOException {
        Charset gbk = Charset.forName("gbk");
//        String fileEncode = EncodeUtil.getEncode(filePath,true);
        return unzip(ins, gbk);
    }

    /**
     * 解压获取所有文件
     * @param ins
     * @return
     * @throws IOException
     */
    public static List<FileModel> unzip(InputStream ins, Charset charset) throws IOException {
        List<FileModel> models = new ArrayList<>();
        ZipInputStream zins = new ZipInputStream(ins, charset);
//        BufferedInputStream bs = new BufferedInputStream(zins);
        ZipEntry ze;
        byte[] bytes = null;
        while ((ze = zins.getNextEntry()) != null) {
            if (!ze.isDirectory()) {
                ByteArrayOutputStream baous = new ByteArrayOutputStream();
                String name = ze.getName();
                IOUtils.copy(zins, baous);

                models.add(FileModel.builder()
                        .name(name)
                        .bytes(baous.toByteArray())
                        .build());

                baous.flush();
                baous.close();
            }
        }
        return models;
    }

    /**
     *
     */
    @Data
    @Builder
    public static class FileModel implements Serializable {
        /**
         * 文件名
         */
        private String name;
        /**
         * 文件类型
         */
        private String type;
        /**
         * 内存字节流
         */
        private byte[] bytes;


        public String getType() {
            if (name!=null) {
                return name.substring(name.lastIndexOf("."), name.length());
            }
            return type;
        }
    }
}
