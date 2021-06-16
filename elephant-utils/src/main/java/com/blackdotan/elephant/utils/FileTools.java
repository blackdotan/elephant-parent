package com.blackdotan.elephant.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/12/4.
 */
public class FileTools {

	public static String getPrefix(String filename) {
		return FilenameUtils.getPrefix(filename);
	}

	public static String getSimpleName(File file) {
		return getSimpleName(file.getPath());
	}

	public static String getSimpleName(String filename) {
		return FilenameUtils.getName(filename);
	}

	public static String getSuffix(String filename) {
		return FilenameUtils.getExtension(filename);
	}


}
