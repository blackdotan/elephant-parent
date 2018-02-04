package com.ipukr.elephant.cloud.storage.backend;

import com.google.gson.Gson;
import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.context.Context;
import com.ipukr.elephant.cloud.storage.Storage;
import com.ipukr.elephant.utils.DateUtils;
import com.ipukr.elephant.utils.JsonUtils;
import com.ipukr.elephant.utils.StringUtils;
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
public class QiniuStorage extends AbstractAPI implements Storage {
    private static final Logger logger = LoggerFactory.getLogger(QiniuStorage.class);

    public static final String ACCESS_KEY = "access_key";
    public static final String SECRET_KEY = "secret_key";
    public static final String BUCKET = "bucket";
    public static final String TEMPLATE = "url.template";
    public static final String DOMAIN = "url.domain";

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String template;
    private String domain;

    private UploadManager upload;
    private Auth auth;

    public QiniuStorage(Context context) {
        super(context);
        init();
    }

    private void init() {
        Configuration cfg = new Configuration();

        upload = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        accessKey = context.findStringAccordingKey(ACCESS_KEY);
        secretKey = context.findStringAccordingKey(SECRET_KEY);
        bucket = context.findStringAccordingKey(BUCKET);
        template = context.findStringAccordingKey(TEMPLATE, "{}");
        domain = context.findStringAccordingKey(DOMAIN, "https://127.0.0.1");
        auth = Auth.create(accessKey, secretKey);
    }

    @Override
    public String auth() {
        return auth.uploadToken(bucket);
    }

    @Override
    public void upload(File file) throws IOException {
        String type = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("."));
        this.upload(file, file.getName().concat(type));
    }

    @Override
    public void upload(File file, String rename) throws IOException {
        Date begin = DateUtils.now();
        String auth =  auth();
        String name = StringUtils.easyAppend(template, rename);
        Response response = upload.put(IOUtils.toByteArray(new FileInputStream(file)), name, auth);
        Date end = DateUtils.now();
        logger.debug("七牛上传文件成功, file.name={}, begin={}, end={}, consume={}, response={}", name, begin, end, end.getTime() - begin.getTime(), response);
    }

    @Override
    public void upload(List<File> files) throws IOException {
        Date begin = DateUtils.now();
        String auth =  auth();
        List<String> arr = new ArrayList<>();
        for(File file : files) {
            String type = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("."));
            String name = file.getName().concat(type);
            arr.add(name);
            upload.put(IOUtils.toByteArray(new FileInputStream(file)), name, auth);
        }
        Date end = DateUtils.now();
        logger.debug("七牛上传文件成功, files.name={}, begin={}, end={}, consume={}", JsonUtils.parserObj2String(arr), begin, end, end.getTime() - begin.getTime());
    }

    @Override
    public void upload(byte[] bytes, String filename) throws Exception {
        Date begin = DateUtils.now();
        String auth =  auth();
        String name = StringUtils.easyAppend(template, filename);
        Response response = upload.put(bytes, name, auth);
        Date end = DateUtils.now();
        logger.debug("七牛上传文件成功, file.name={}, begin={}, end={}, consume={}, response={}", name, begin, end, end.getTime() - begin.getTime(), response);
    }

    @Override
    public String address(String filename) {
        String name = StringUtils.easyAppend(template, filename);
        if(domain.endsWith("/")) {
            return domain.concat(name);
        } else {
            return domain.concat("/").concat(name);
        }
    }

    @Override
    public String domain() {
        return domain;
    }
}
