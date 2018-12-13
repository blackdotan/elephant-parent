package com.ipukr.elephant.mybatis.plugins;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmw on 1/12/17.
 */
public class MainHelper {


    @Test
    public void runner() throws Exception {
        List<String> warnings = new ArrayList<String>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        String resource = MainHelper.class.getResource("/").getPath().concat("generatorConfig.xml");
        System.out.println(resource);
        InputStream ins = new FileInputStream(new File(resource));
        Configuration config = cp.parseConfiguration(ins);

        DefaultShellCallback shellCallback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);
        myBatisGenerator.generate(null, null, null);
    }

//    /**
//     * @param args
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception {
//        List<String> warnings = new ArrayList<String>();
//        ConfigurationParser cp = new ConfigurationParser(warnings);
//        String resource = MainHelper.class.getResource("/").getPath().concat("generatorConfig.xml");
//        System.out.println(resource);
//        InputStream ins = new FileInputStream(new File(resource));
//        Configuration config = cp.parseConfiguration(ins);
//
//        DefaultShellCallback shellCallback = new DefaultShellCallback(true);
//        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);
//        myBatisGenerator.generate(null, null, null);
//    }
}

