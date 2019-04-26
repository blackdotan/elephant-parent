package com.ipukr.elephant.utils;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/4/20.
 */
public class ZipUtilsTest {
    @Test
    public void testUnzip() throws IOException {
        String filepath = ZipUtilsTest.class.getResource("/").getPath().concat("保安数据采集模板.xlsx");
        FileInputStream fins = new FileInputStream(filepath);
        List<ZipUtils.FileModel> models = ZipUtils.unzip(fins);
        System.out.println();
    }
}
