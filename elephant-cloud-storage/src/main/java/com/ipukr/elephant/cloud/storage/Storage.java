package com.ipukr.elephant.cloud.storage;

import com.qiniu.common.QiniuException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by ryan on 下午1:46.
 */
public interface Storage {

    String domain();

    String address(String filename);

    String auth();

    void upload(File file) throws IOException;

    void upload(File file, String rename) throws IOException;

    void upload(List<File> files) throws IOException;

    void upload(byte[] bytes, String filename) throws QiniuException, Exception;

}
