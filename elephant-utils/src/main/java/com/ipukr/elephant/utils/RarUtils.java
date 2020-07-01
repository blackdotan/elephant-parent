package com.ipukr.elephant.utils;


import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LYX on 2019/9/16.
 */
public class RarUtils {

	/**
	 * Un rar list.
	 *
	 * @param source the source
	 * @return the list
	 * @throws Exception the exception
	 */
	public static List<ZipUtils.FileModel> unrar(File source) throws Exception {
		List<ZipUtils.FileModel> files = new ArrayList<>();
		Archive archive = new Archive(source);
		FileHeader header = archive.nextFileHeader();
		while(header!=null){
			if (!header.isDirectory()) {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				archive.extractFile(header, outputStream);
				String fileName = header.getFileNameW();
				files.add(com.ipukr.elephant.utils.ZipUtils.FileModel.builder().name(fileName).bytes(outputStream.toByteArray()).build());
				outputStream.flush();
				outputStream.close();
			}
			header = archive.nextFileHeader();
		}
		return files;
	}
}
