package com.ipukr.elephant.dssclient;

import java.io.FileOutputStream;
import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2020/3/31.
 */
public interface Client {

	/**
	 * 组织树
	 * @return
	 */
	List<Object> tree();

	/**
	 * 快照
	 * @param device
	 * @return
	 */
	List<Object> snapshot(String device);

	/**
	 * 快照
	 * @param device
	 * @param channel
	 * @return
	 */
	String snapshot(String device, String channel);


	boolean gerReal();

	boolean closeReal();
}
