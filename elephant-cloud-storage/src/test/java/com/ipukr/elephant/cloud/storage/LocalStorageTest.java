package com.ipukr.elephant.cloud.storage;

import com.ipukr.elephant.application.factory.Factory;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
public class LocalStorageTest {

    private Storage mStorage;

    @Before
    public void setUp() throws Exception {
        mStorage = Factory.getInstance().build("config.properties", "storage.local");
    }

    @Test
    public void test() throws Exception {
        File file = new File(this.getClass().getResource("/").getPath().concat("test.wav"));
        mStorage.upload(IOUtils.toByteArray(new FileInputStream(file)),"Hekki.wav");
    }

    @Test
    public void test2() throws Exception {
        File file = new File(this.getClass().getResource("/").getPath().concat("test.wav"));
        mStorage.upload(file);
    }

    @Test
    public void test3() throws Exception {
        File file = new File(this.getClass().getResource("/").getPath().concat("test.wav"));
        mStorage.upload(file, "ssss.wav");
    }

    @Test
    public void test4() throws Exception {
        File file1 = new File(this.getClass().getResource("/").getPath().concat("test.wav"));
        File file2 = new File(this.getClass().getResource("/").getPath().concat("test.jpg"));
        List<File> files = new ArrayList<>();
        files.add(file1);
        files.add(file2);
        mStorage.upload(files);
    }

}
