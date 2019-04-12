package com.ipukr.elephant.cloud.storage.backend;

import com.google.gson.Gson;
import com.ipukr.elephant.cloud.storage.Storage;
import com.ipukr.elephant.cloud.storage.config.QiniuStorageConfig;

import com.ipukr.elephant.cloud.storage.domain.QiniuUploadResponse;
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
import java.io.*;
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
    public QiniuUploadResponse upload(File file) throws Exception {
        String type = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("."));
        return upload(IOUtils.toByteArray(new FileInputStream(file)), file.getName().concat(type));
    }

    @Override
    public QiniuUploadResponse upload(File file, String rename) throws Exception {
        String auth =  auth();
        return upload(IOUtils.toByteArray(new FileInputStream(file)), rename);
    }

    @Override
    public List<QiniuUploadResponse> upload(List<File> files) throws Exception {
        String auth =  auth();
        List<QiniuUploadResponse> arr = new ArrayList<QiniuUploadResponse>();
        boolean bool = true;
        for(File file : files) {
            String type = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("."));
            String name = file.getName().concat(type);
            QiniuUploadResponse response = upload(IOUtils.toByteArray(new FileInputStream(file)), file.getName().concat(type));
            arr.add(response);
        }
        return arr;
    }

    @Override
    public QiniuUploadResponse upload(byte[] bytes, String filename) throws Exception {
        String auth =  auth();
        Response response = upload.put(bytes, filename, auth);
        QiniuUploadResponse.QiniuUploadResponseBuilder builder = QiniuUploadResponse.builder();

        if (response.statusCode == 200) {
            DefaultPutRet dpret = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            builder.success(true)
                    .filename(filename)
                    .hash(dpret.hash)
                    .key(dpret.key)
                    .build();
        } else {
            builder.success(false);
        }
        return builder.build();
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
