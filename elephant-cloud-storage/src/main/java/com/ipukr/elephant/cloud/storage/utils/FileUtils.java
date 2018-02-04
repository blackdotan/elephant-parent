package com.ipukr.elephant.cloud.storage.utils;

import java.io.*;

/**
 * Created by Administrator on 2017/9/15.
 */
public class FileUtils {

    /**
     * 将byte字节流转换成file文件，并输出到指定位置
     * @param bytes
     * @param outputFile
     * @return
     */
    public static File getFileFromBytes(byte[] bytes, String outputFile){
        BufferedOutputStream stream = null;
        File file = null;
        try{
            file = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * 拷贝文件至指定目录
     * @param target 源文件，要复制的文件
     * @param dest  目标文件的地址
     */
    public static void copyFile(File target,String dest){
        String filename=target.getName();
        File destFile=new File(dest);
        try {
            //先进行输入才能进行输出，代码书序不能变
            InputStream in=new FileInputStream(target);
            OutputStream out=new FileOutputStream(destFile);
            byte[] bytes=new byte[1024];
            int len=-1;

            while((len=in.read(bytes))!=-1){
                out.write(bytes, 0, len);
            }
            // TODO 流关闭代码, 未考虑异常情况
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
