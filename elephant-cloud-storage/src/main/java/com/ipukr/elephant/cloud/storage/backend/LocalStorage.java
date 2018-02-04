package com.ipukr.elephant.cloud.storage.backend;

import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.context.Context;
import com.ipukr.elephant.cloud.storage.Storage;
import com.ipukr.elephant.cloud.storage.utils.FileUtils;
import com.qiniu.common.QiniuException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
public class LocalStorage extends AbstractAPI implements Storage{

    private static final Logger logger = LoggerFactory.getLogger(LocalStorage.class);

    public static final String File_Absolute_Address = "file_absolute_address";

    private String fileAbsoluteAddress;

    public LocalStorage(Context context) {
        super(context);
        this.init();
    }

    private void init() {
        fileAbsoluteAddress = context.findStringAccordingKey(File_Absolute_Address);
    }

    // TODO 此处可以为返回域名信息
    @Override
    public String domain() {
        return null;
    }

    // TODO 此处可以为返回:域名信息/文件名
    @Override
    public String address(String filename) {
        return null;
    }

    @Override
    public String auth() {
        return null;
    }

    @Override
    public void upload(File file) throws IOException {
        FileUtils.copyFile(file, fileAbsoluteAddress + "\\" + file.getName());
        logger.debug("文件复制成功，新文件地址为:{}", fileAbsoluteAddress + "\\" + file.getName());
    }

    @Override
    public void upload(File file, String rename) throws IOException {
        FileUtils.copyFile(file, fileAbsoluteAddress + "\\" + rename);
        logger.debug("文件复制成功，新文件地址为:{}", fileAbsoluteAddress + "\\" + rename);
    }

    @Override
    public void upload(List<File> files) throws IOException {
        for(File file : files) {
            FileUtils.copyFile(file, fileAbsoluteAddress + "\\" + file.getName());
            logger.debug("文件复制成功，新文件地址为:{}", fileAbsoluteAddress + "\\" + file.getName());
        }
    }

    @Override
    public void upload(byte[] bytes, String filename) throws QiniuException, Exception {
        FileUtils.getFileFromBytes(bytes, fileAbsoluteAddress + "\\" + filename);
        logger.debug("本地文件保存成功，文件地址为:{}", fileAbsoluteAddress + "\\" + filename);
    }
}
