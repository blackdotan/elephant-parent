package com.ipukr.elephant.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 上午10:23.
 */
public class PathUtils {

    private static final String FILE_SCHEME = "file:";

    public static String compatible(String path, String sep) {
        return path.replace(sep, File.separator);
    }

    public static String compatible(String path) {
        return path.replace("/", File.separator);
    }

    /**
     * @param paths
     * @return
     */
    public static String[] compatible(String[] paths) {
        String[] arr = new String[paths.length];
        for(int i=0; i< paths.length; i++) {
            arr[i] = compatible(paths[i]);
        }
        return arr;
    }
}
