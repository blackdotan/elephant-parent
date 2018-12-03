package com.ipukr.elephant.cloud.storage;

import com.ipukr.elephant.cloud.storage.backend.QiniuStorage;
import com.ipukr.elephant.cloud.storage.config.QiniuStorageConfig;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by ryan on 下午2:58.
 */
public class StorageTest {

    private QiniuStorage mStorage;

    /**
     * 七牛存储配置
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        QiniuStorageConfig config = QiniuStorageConfig.custom()
                .accessKey("TIfWJ8n_YCiOfnA8O4eOx79VdgfduHQO0svjhO2K")
                .secretKey("960i9OllMoLEkUfkPPV0UpnE4c9Qg2DPIRFv_tN1")
                .domain("https://oustorage.ipukr.cn/")
                .bucket("outsourcing")
                .build();
        mStorage = new QiniuStorage(config);
    }

    /**
     * 测试上传文件到七牛
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        File file = new File(this.getClass().getResource("/").getPath().concat("test.jpg"));
        mStorage.upload(IOUtils.toByteArray(new FileInputStream(file)),"Hekki.jpg");
    }

    /**
     * 测试获取域名
     * @throws Exception
     */
    @Test
    public void testdomain() throws Exception {
        String domain = mStorage.domain();
        System.out.println(domain);
    }

    /**
     * 测试拼接文件地址
     * @throws Exception
     */
    @Test
    public void testaddress() throws Exception {
        String domain = mStorage.address("Hekki.jpg");
        System.out.println(domain);
    }
}
