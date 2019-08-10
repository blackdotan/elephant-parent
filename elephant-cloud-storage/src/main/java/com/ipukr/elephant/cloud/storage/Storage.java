package com.ipukr.elephant.cloud.storage;

import com.ipukr.elephant.cloud.storage.domain.UploadResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by ryan on 下午1:46.
 */
public interface Storage {

    /**
     * 获取域名
     * @return 域名
     */
    String domain();

    /**
     * 根据[文件名]获取文件地址
     * @param filename 文件名
     * @return 文件地址
     */
    String address(String filename);

    /**
     * 获取授权Token
     *
     * @return Token
     */
    String auth();

    /**
     * 上传文件
     *
     * @param file 上传文件
     * @throws IOException IO异常
     */
    UploadResponse upload(File file) throws Exception;

    /**
     * 上传文件
     *
     * @param file 上传文件
     * @param rename 重命名
     * @throws IOException IO异常
     */
    UploadResponse upload(File file, String rename) throws Exception;

    /**
     * 批量上传文件
     *
     * @param files 上传文件
     * @throws IOException IO异常
     */
    List<UploadResponse> upload(List<File> files) throws Exception;

    /**
     * 上传文件
     *
     * @param bytes 文件字节流
     * @param filename 重命名
     * @throws IOException IO异常
     */
    UploadResponse upload(byte[] bytes, String filename) throws Exception;

}
