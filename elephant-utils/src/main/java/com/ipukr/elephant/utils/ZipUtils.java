package com.ipukr.elephant.utils;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.apache.poi.UnsupportedFileFormatException;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

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
     * 压缩文件到目标输出流
     * @param files
     * @param ous
     * @throws IOException
     */
    public static void compress(File[] files, OutputStream ous) throws IOException {
        byte[] buffer = new byte[1024];
        ZipOutputStream zous = new ZipOutputStream(ous);
        for (int i = 0; i < files.length; i++) {
            FileInputStream fins = new FileInputStream(files[i]);
            ZipEntry entry = new ZipEntry(files[i].getName());

            zous.putNextEntry(entry);
            int length = 0;
            while ((length = fins.read(buffer)) != -1) {
                zous.write(buffer, 0, length);
            }
            fins.close();
            zous.closeEntry();
            zous.flush();
        }
        zous.finish();
        zous.close();
    }



    /**
     * @param fm
     * @param ous
     * @throws IOException
     */
    public static void compress(FileModel fm, OutputStream ous) throws IOException {
        compress(Arrays.asList(fm), ous);
    }

    /**
     * @param fms
     * @param ous
     * @throws IOException
     */
    public static void compress(List<FileModel> fms, OutputStream ous) throws IOException {
        ZipOutputStream zous = new ZipOutputStream(ous);
        byte[] buffer = new byte[1024];
        for (FileModel fm : fms) {
//            ByteArrayInputStream bais = new ByteArrayInputStream(fm.getBytes());
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(fm.getBytes()));
            ZipEntry entry = new ZipEntry(fm.getName());
            zous.putNextEntry(entry);
            int length = 0;
            while ((length = dis.read(buffer)) != -1) {
                zous.write(buffer, 0, length);
            }
            zous.closeEntry();
            zous.flush();
        }
        zous.finish();
        zous.close();
    }



    /**
     * 压缩文件到目标输出流
     * @param files
     * @param ous
     * @throws IOException
     */
    public static void pcompress(File[] files, OutputStream ous) throws IOException {
        byte[] buffer = new byte[1024];
        ZipOutputStream zous = new ZipOutputStream(ous);
        for (int i = 0; i < files.length; i++) {
            FileInputStream fins = new FileInputStream(files[i]);
            ZipEntry entry = new ZipEntry(files[i].getPath());

            zous.putNextEntry(entry);
            int length = 0;
            while ((length = fins.read(buffer)) != -1) {
                zous.write(buffer, 0, length);
            }
            fins.close();
            zous.closeEntry();
            zous.flush();
        }
        zous.finish();
        zous.close();
    }



    /**
     * @param filename
     * @param ins
     * @param charset
     * @return
     * @throws IOException
     * @throws RarException
     */
    public static List<FileModel> extract(String filename, InputStream ins, Charset charset) throws IOException, RarException {
        if (filename.endsWith(".rar")) {
            return unrar(ins, charset);
        } else if (filename.endsWith(".zip")) {
            return unzip(ins, charset);
        } else {
            throw new RuntimeException(String.format("不支持解压当前的文件格式，format=%s"));
        }
    }

    /**
     * 从输入流解压出文件数据
     * @param ins
     * @return
     * @throws IOException
     */
    public static List<FileModel> unzip(InputStream ins) throws IOException {
        Charset gbk = Charset.forName("gbk");
        return unzip(ins, gbk);
    }


    /**
     * 解压获取所有文件
     * @param ins
     * @param charset
     * @return
     * @throws IOException
     */
    public static List<FileModel> unzip(InputStream ins, Charset charset) throws IOException {
        List<FileModel> models = new ArrayList<>();
        ZipInputStream zins = new ZipInputStream(ins, charset);
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
     * 解压RAR
     * @param ins
     * @param charset
     * @return
     * @throws IOException
     * @throws RarException
     */
    public static List<FileModel> unrar(InputStream ins, Charset charset) throws IOException, RarException {
        File tmpfile = persistToTempFile(ins, StringUtils.uuid().toString());
        List<FileModel> models = new ArrayList<>();

        Archive archive = new Archive(tmpfile);
        archive.getMainHeader().print();

        FileHeader fh = null;
        while(( fh = archive.nextFileHeader() ) !=null){
            ByteArrayOutputStream baous = new ByteArrayOutputStream();
            archive.extractFile(fh, baous);

            models.add(FileModel.builder()
                    .name(fh.getFileNameString())
                    .bytes(baous.toByteArray())
                    .build());
            baous.flush();
            baous.close();
        }
        return models;
    }




    //每次读取的大小 1KB
    private static final int BYTESIZE = 1024;


    /**
     * 存储临时文件
     * @param is
     * @param fileName
     * @return
     */
    private static File persistToTempFile(InputStream is, String fileName){
        String tmpdir = "";
        try {
            tmpdir = URLDecoder.decode(ZipUtils.class.getClassLoader().getResource("../temp").getPath(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        File tmpfile = new File(tmpdir + fileName);

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try{
            bis = new BufferedInputStream(is);
            // 把文件流转为文件，保存在临时目录
            bos = new BufferedOutputStream(new FileOutputStream(tmpfile));
            int len = 0;
            //缓冲区
            byte[] buf = new byte[10*BYTESIZE];
            while((len=bis.read(buf)) != -1){
                bos.write(buf, 0, len);
            }
            bos.flush();
            IOTools.close(bos);
            IOTools.close(bis);
        } catch (IOException e){
            e.printStackTrace();
        }
        return tmpfile;
    }

    /**
     * 简单文件对象，用于返回文件数据及文件信息
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
