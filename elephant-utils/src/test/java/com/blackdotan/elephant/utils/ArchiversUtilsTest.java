package com.blackdotan.elephant.utils;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.junit.Test;

import java.io.*;
import java.util.Map;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/4/11.
 */
public class ArchiversUtilsTest {

    @Test
    public void test() throws IOException, ArchiveException {
        String zipfile = this.getClass().getResource("/").getPath().concat("outsourcing-configuration.zip");
        File file = new File(zipfile);
        System.out.println(file.exists());
        FileInputStream ins = new FileInputStream(file);
//        ZipArchiveInputStream zains = new ZipArchiveInputStream(ins);
        Map<String, ByteArrayOutputStream> result = ArchiversUtils.decompress(ArchiveStreamFactory.ZIP, ins);

        for (Map.Entry<String, ByteArrayOutputStream> entry : result.entrySet()) {

            String filename = entry.getKey();
            ByteArrayOutputStream baous = entry.getValue();
            File f = new File(this.getClass().getResource("/").getPath().concat(filename));
            mkdir(f.getParentFile());
            FileOutputStream fous = new FileOutputStream(f);
            baous.writeTo(fous);

        }
    }

    public void mkdir(File file) {
        if (!file.exists()) {
            mkdir(file.getParentFile());
            file.mkdir();
        }
    }
}
