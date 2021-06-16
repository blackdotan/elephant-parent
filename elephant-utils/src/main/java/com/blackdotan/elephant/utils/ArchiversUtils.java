package com.blackdotan.elephant.utils;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/8/13.
 */
public class ArchiversUtils {


    /**
     * @param ins
     * @return
     * @throws IOException
     * @throws ArchiveException
     */
    public static Map<String, ByteArrayOutputStream> decompress(String archiverName, InputStream ins) throws IOException, ArchiveException {
        ArchiveInputStream input = new ArchiveStreamFactory().createArchiveInputStream(archiverName, ins);
        ArchiveEntry entry = null;
        Map<String, ByteArrayOutputStream> result = new HashMap<>();
        while ((entry = input.getNextEntry()) != null) {
            if (!entry.isDirectory()) {
                ByteArrayOutputStream baous = new ByteArrayOutputStream();
                String name = entry.getName();
                IOUtils.copy(input, baous);
                baous.flush();
                baous.close();
                result.put(name, baous);
            }
        }
        input.close();
        return result;
    }
}
