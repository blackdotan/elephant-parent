package com.ipukr.elephant.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/12/4.
 */
public class IOTools {
	/**
	 * 关闭流
	 * @param closeable
	 */
	public static void close(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
			closeable = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
