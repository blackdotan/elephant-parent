package com.ipukr.elephant.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 上午10:23.
 */
public class PathUtils {

    private static final String FILE_SCHEME = "file:";

    /**
     * 路径格式兼容处理
     * @param path window格式或Linux格式 路径
     * @param sep 分隔符
     * @return 当前路径格式
     */
    public static String compatible(String path, String sep) {
        return path.replace(sep, File.separator);
    }

    /**
     * 路径格式兼容处理
     * @param path window格式或Linux格式 路径
     * @return 当前路径格式
     */
    public static String compatible(String path) {
        return path.replace("/", File.separator);
    }

    /**
     * 路径格式兼容处理
     * @param paths window格式或Linux格式 路径 列表
     * @return 当前路径格式
     */
    public static String[] compatible(String[] paths) {
        String[] arr = new String[paths.length];
        for(int i=0; i< paths.length; i++) {
            arr[i] = compatible(paths[i]);
        }
        return arr;
    }
}
