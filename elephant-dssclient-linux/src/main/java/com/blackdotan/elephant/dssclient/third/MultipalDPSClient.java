package com.blackdotan.elephant.dssclient.third;

import com.blackdotan.elephant.dssclient.config.DPSClientConfig;
import com.blackdotan.elephant.dssclient.domain.DPChannel;
import com.blackdotan.elephant.dssclient.domain.DPDevice;
import com.blackdotan.elephant.dssclient.domain.DPOrganization;
import com.blackdotan.elephant.dssclient.domain.DPSnapshot;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2020/3/31.
 */
@Slf4j
public class MultipalDPSClient extends DPSClient {



	final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


	public MultipalDPSClient(DPSClientConfig config) {
		super(config);
	}


	@Override
	public List<DPSnapshot> snapshot(DPDevice device) throws Exception {
		if (this.organization == null) {
			this.fetgroup();
		}
		List<DPSnapshot> arr = new ArrayList<>();

		// 解析设备，获取通道
		List<DPOrganization.Devices.DeviceX.UnitNodes> uns = organization.getDevices().getDevices().stream().filter(e -> {
			return e.getId() == device.getId();
		}).flatMap(e-> {
			return e.getUnitNodes().stream();
		}).filter(n-> {
			return n.getType() == 1;
		}).collect(Collectors.toList());


		// 声明线程列表
		final List<Callable<DPSnapshot>> partitions = new ArrayList<Callable<DPSnapshot>>(0);

		int offset = 0;
		for ( DPOrganization.Devices.DeviceX.UnitNodes.ChannelX channelX : uns.stream().flatMap(e ->e.getChannel().stream()).collect(Collectors.toList())) {
			DPChannel channel = DPChannel.builder()
					.id(channelX.getId())
					.offset(offset++)
					.build();
			partitions.add(new DPSSnapshotCallable(m_nDLLHandle, device, channel));
		}

		final List<Future<DPSnapshot>> futures;
		// 执行线程
		futures = pool.invokeAll(partitions, 15000, TimeUnit.SECONDS);
		for (final Future<DPSnapshot> result : futures) {
			arr.add(result.get());
		}

		return arr;
	}





}
