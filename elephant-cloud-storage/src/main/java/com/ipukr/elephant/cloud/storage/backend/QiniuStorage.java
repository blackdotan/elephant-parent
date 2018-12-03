package com.ipukr.elephant.cloud.storage.backend;

import com.google.gson.Gson;
import com.ipukr.elephant.cloud.storage.Storage;
import com.ipukr.elephant.cloud.storage.config.QiniuStorageConfig;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ryan on 下午1:46.
 */
//@Component
public class QiniuStorage implements Storage {

    private static final Logger logger = LoggerFactory.getLogger(QiniuStorage.class);

    private QiniuStorageConfig config;

    private UploadManager upload;

    private Auth auth;

    public QiniuStorage(QiniuStorageConfig config) {
        this.config = config;
        this.init();
    }

    private void init() {
        logger.debug("初始化组件 {}, config={}", QiniuStorage.class.getCanonicalName(), config.toString());
        Configuration cfg = new Configuration();
        upload = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        auth = Auth.create(config.getAccessKey(), config.getSecretKey());
    }



    @Override
    public String auth() {
        return auth.uploadToken(config.getBucket());
    }

    /**
     *
     * @param file 上传文件
     * @throws IOException
     */
    @Override
    public boolean upload(File file) throws IOException {
        String type = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("."));
        return this.upload(file, file.getName().concat(type));
    }

    @Override
    public boolean upload(File file, String rename) throws IOException {
        String auth =  auth();
        Response response = upload.put(IOUtils.toByteArray(new FileInputStream(file)), rename, auth);
        return response.statusCode == 200;
    }

    @Override
    public boolean upload(List<File> files) throws IOException {
        String auth =  auth();
        List<String> arr = new ArrayList<>();
        boolean bool = true;
        for(File file : files) {
            String type = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("."));
            String name = file.getName().concat(type);
            arr.add(name);
            Response response = upload.put(IOUtils.toByteArray(new FileInputStream(file)), name, auth);
            bool = bool &&  response.statusCode == 200;
        }
        return bool;
    }

    @Override
    public boolean upload(byte[] bytes, String filename) throws Exception {
        String auth =  auth();
        Response response = upload.put(bytes, filename, auth);
        return response.statusCode == 200;
    }

    @Override
    public String address(String filename) {
        if(config.getDomain().endsWith("/")) {
            return config.getDomain().concat(filename);
        } else {
            return config.getDomain().concat("/").concat(filename);
        }
    }

    @Override
    public String domain() {
        return config.getDomain();
    }


}
