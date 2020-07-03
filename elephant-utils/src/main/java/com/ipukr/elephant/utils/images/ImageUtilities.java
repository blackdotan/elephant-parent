package com.ipukr.elephant.utils.images;

import com.ipukr.elephant.utils.IOTools;
import net.coobird.thumbnailator.Thumbnails;
import sun.misc.BASE64Encoder;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 请描述类 <br>
 *
 * @author ryan wu <p> Created by ryan wu on 2019/6/20.
 */
public class ImageUtilities {

    /**
     * Tobase 64 string.
     *
     * @param bytes the bytes
     * @return the string
     * @throws IOException the io exception
     */
    public static String tobase64(byte[] bytes) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(bytes, 0, bytes.length);
        // 内存流 转 Base64 编码
        BASE64Encoder encoder = new BASE64Encoder();
        while (baos.size() > 500 * 1024) {
            baos = compress(baos, 0.8f, 0.2f, true);
        }
        String base64 = encoder.encode(baos.toByteArray());

        return base64;
    }

    /**
     * Tobase 64 string.
     *
     * @param imgURL the img url
     * @return string string
     */
    public static String tobase64(String imgURL) {
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


            while (baos.size() > 500 * 1024) {
                baos = compress(baos, 0.8f, 0.2f, true);
            }

            // 内存流 转 Base64 编码
            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encode(baos.toByteArray());

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
     * @param scale   the scale
     * @param quality the quality
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


}
