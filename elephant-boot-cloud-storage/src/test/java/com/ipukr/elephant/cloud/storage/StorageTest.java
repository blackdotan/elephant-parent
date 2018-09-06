package com.ipukr.elephant.cloud.storage;

import com.ipukr.elephant.cloud.storage.backend.QiniuStorage;
import com.ipukr.elephant.cloud.storage.config.QiniuStorageConfig;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by ryan on 下午2:58.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QiniuStorageConfig.class, QiniuStorage.class})
@EnableConfigurationProperties
public class StorageTest {

    @Autowired
    private QiniuStorage mStorage;


    @Test
    public void test() throws Exception {
        File file = new File(this.getClass().getResource("/").getPath().concat("test.jpg"));
        mStorage.upload(IOUtils.toByteArray(new FileInputStream(file)),"Hekki.jpg");
    }

    @Test
    public void testdomain() throws Exception {
        String domain = mStorage.domain();
        System.out.println(domain);
    }

    @Test
    public void testaddress() throws Exception {
        String domain = mStorage.address("Hekki.jpg");
        System.out.println(domain);
    }
}
