package com.ipukr.elephant.dssclient;

import com.ipukr.elephant.dssclient.config.DPSClientConfig;
import com.ipukr.elephant.dssclient.domain.DPDevice;
import com.ipukr.elephant.dssclient.domain.DPOrganization;
import com.ipukr.elephant.dssclient.domain.res.DPSnapshot;
import com.ipukr.elephant.dssclient.third.DPSClient;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;


/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2020/3/31.
 */
public class ClientTest {
	@Test
	public void dotst() throws IOException, JAXBException {
		DPSClientConfig config = new DPSClientConfig();
		Client client = new DPSClient(config);
		DPOrganization iDPOrganization = client.group();
		DPOrganization.Devices.DeviceX dx = iDPOrganization.getDevices().getDevices().stream().filter(e->e.getName().matches("")).findFirst().get();

		// 抓取设备快照
		DPDevice dpd = DPDevice.builder()
				.id(dx.getId())
				.name(dx.getName())
				.build();
		List<DPSnapshot> snapshots = client.snapshot(dpd);
	}
}
