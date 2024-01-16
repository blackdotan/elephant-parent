package com.blackdotan.elephant.utils.images;

import com.blackdotan.elephant.utils.IOTools;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 请描述类 <br>
 *
 * @author ryan wu <p> Created by ryan wu on 2019/6/20.
 */
public class ImageUtilities {

    /**
     * Tobase 64 string. / compressed
     *
     * @param bytes the bytes
     * @return the string
     * @throws IOException the io exception
     */
    public static String tobase64(byte[] bytes) throws IOException {
       return tobase64(bytes, 500 * 1024, 0.8f, 0.2f);
    }

    /**
     * Tobase 64 string. / compressed
     *
     * @param bytes the bytes
     * @param maxSize 最大大小
     * @param scale 缩放比例
     * @param quality 图片质量 / 0.1 ~ 1.0
     * @return the string
     * @throws IOException the io exception
     */
    public static String tobase64(byte[] bytes, int maxSize, float scale, float quality) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(bytes, 0, bytes.length);
        while (baos.size() > maxSize) {
            // baos = compress(baos, 0.8f, 0.2f, true);
            baos = compress(baos, scale, quality, true);
        }
        // 内存流 转 Base64 编码
        //BASE64Encoder encoder = new BASE64Encoder();
        //String base64 = encoder.encode(baos.toByteArray());
        String base64 = Base64.encodeBase64String(baos.toByteArray());
        return base64;
    }

    /**
     * @param imgURL / compressed
     * @return
     */
    public static String tobase64(String imgURL) {
        return tobase64(imgURL, 500 * 1024, 0.8f, 0.2f);
    }

    /**
     * Tobase 64 string. / compressed
     *
     * @param imgURL the img url
     * @param size bit size 最大保存体积
     * @param scale   压缩比例
     * @param quality
     * @return string string
     */
    public static String tobase64(String imgURL, int size, float scale, float quality) {
        ByteArrayOutputStream baos = null;
        try {
            // 流
            baos = new ByteArrayOutputStream();
            // 创建链接
            URL url = new URL(imgURL);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);
            if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                //连接失败/链接失效/图片不存在
                return "fail";
            }
            InputStream ins = conn.getInputStream();
            // 网络流 转 内存流
            byte[] data = new byte[1024];
            int len = -1;
            while ((len = ins.read(data)) != -1) {
                baos.write(data, 0, len);
            }
            // 关闭网络流
            IOTools.close(ins);

            while (baos.size() > size) {
                // baos = compress(baos, 0.8f, 0.2f, true);
                baos = compress(baos, scale, quality, true);
            }

            // 内存流 转 Base64 编码
            //BASE64Encoder encoder = new BASE64Encoder();
            //String base64 = encoder.encode(baos.toByteArray());
            String base64 = Base64.encodeBase64String(baos.toByteArray());

            return base64;
        } catch (Exception e) {
            System.out.println("error=> " + imgURL);
            e.printStackTrace();
        } finally {
            IOTools.close(baos);
        }
        return "fail";
    }

//    /**
//     * Compress byte array output stream.
//     *
//     * @param baos    the baos
//     * @param scale   the scale
//     * @param quality the quality
//     * @param close   the close
//     * @return the byte array output stream
//     * @throws IOException the io exception
//     */
//    public static ByteArrayOutputStream compress(ByteArrayOutputStream baos, float scale, float quality, boolean close) throws IOException {
//        ByteArrayOutputStream nbaos = compress(baos, scale, quality);
//        if (close) {
//            IOTools.close(baos);
//        }
//        return nbaos;
//    }

    /**
     * Compress byte array output stream.
     *
     * @param baos    the baos
     * @param scale   the scale / 缩放比例
     * @param quality the quality /
     * @return the byte array output stream
     * @throws IOException the io exception
     */
    public static ByteArrayOutputStream compress(ByteArrayOutputStream baos, float scale, float quality, boolean close) throws IOException {
        ByteArrayOutputStream nbaos = new ByteArrayOutputStream();
        InputStream ins = new ByteArrayInputStream(baos.toByteArray());
        Thumbnails.of(ins).scale(scale).outputQuality(quality).toOutputStream(nbaos);
        if (close) {
            IOTools.close(baos);
        }
        IOTools.close(ins);
        return nbaos;
    }

    public static boolean base64StrToImage(String imgStr, String path) {
        if (imgStr == null) {
            return false;
        }
        //BASE64Decoder decoder = new BASE64Decoder();

        try {
            // 解密
            byte[] b = Base64.decodeBase64(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //文件夹不存在则自动创建
            File tempFile = new File(path);
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            OutputStream out = new ByteArrayOutputStream();
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
