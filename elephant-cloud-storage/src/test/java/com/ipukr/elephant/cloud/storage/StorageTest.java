package com.ipukr.elephant.cloud.storage;

import com.ipukr.elephant.architecture.factory.Factory;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by ryan on 下午2:58.
 */
public class StorageTest {

    Storage mStorage;
    @Before
    public void setUp() throws Exception {
        mStorage = Factory.getInstance().build();
    }

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
