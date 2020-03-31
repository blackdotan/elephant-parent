package com.ipukr.elephant.dssclient.third;

import com.ipukr.elephant.dssclient.Client;

import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2020/3/31.
 */
public class DPSClient implements Client {

	@Override
	public List<Object> tree() {
		return null;
	}

	@Override
	public List<Object> snapshot(String device) {
		return null;
	}

	@Override
	public String snapshot(String device, String channel) {
		return null;
	}

	@Override
	public boolean gerReal() {
		return false;
	}

	@Override
	public boolean closeReal() {
		return false;
	}


}
