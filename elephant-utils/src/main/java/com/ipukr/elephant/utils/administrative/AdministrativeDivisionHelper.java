package com.ipukr.elephant.utils.administrative;

import com.ipukr.elephant.utils.language.PinyinUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 行政区划工具
 */
public class AdministrativeDivisionHelper {
    /**
     *
     */
    private List<ProvinceDivisionInfo> pdfs = null;

    private static volatile AdministrativeDivisionHelper singleton = null;

    /**
     *
     */
    private AdministrativeDivisionHelper() {
        if(pdfs == null) {
            // 初始化拼音对象
            synchronized(AdministrativeDivisionHelper.class){
                if (pdfs == null) {
                    pdfs = new ArrayList<>();
                    InputStream ins = PinyinUtils.class.getResourceAsStream("/provinces.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(ins));
                    String s = null;
                    try {
                        while ((s = br.readLine()) != null) {
                            if (s != null) {
                                String[] fields = s.split(";");
                                ProvinceDivisionInfo.builder()
                                        .mc(fields[0])
                                        .jc(fields[1])
                                        .bm(fields[3])
                                        .build();
                                pdfs.add(
                                        ProvinceDivisionInfo.builder()
                                                .mc(fields[0])
                                                .jc(fields[1])
                                                .bm(fields[3])
                                                .build());
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    /**
     * @return
     */
    public static synchronized  AdministrativeDivisionHelper getInstance() {
        if (singleton == null) {
            synchronized (AdministrativeDivisionHelper.class) {
                if (singleton == null) {
                    singleton = new AdministrativeDivisionHelper();
                }
            }
        }
        return singleton;
    }

    /**
     * @param bm
     * @return
     */
    public ProvinceDivisionInfo findByBm(String bm) {
        return findByBm(bm, null);
    }
    /**
     * g
     * @param bm
     * @return
     */
    public ProvinceDivisionInfo findByBm(String bm, ProvinceDivisionInfo def) {
        return pdfs.stream().filter(e->e.getBm().equals(bm)).findFirst().orElse(def);
    }

    /**
     * @param bm
     * @return
     */
    public ProvinceDivisionInfo findByJc(String bm) {
        return findByJc(bm, null);
    }

    /**
     * @param jc
     * @param def
     * @return
     */
    public ProvinceDivisionInfo findByJc(String jc, ProvinceDivisionInfo def) {
        return pdfs.stream().filter(e->e.getJc().equals(jc)).findFirst().orElse(def);
    }


}
