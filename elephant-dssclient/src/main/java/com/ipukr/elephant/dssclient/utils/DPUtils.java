package com.ipukr.elephant.dssclient.utils;

import com.ipukr.elephant.dssclient.domain.DPChannel;
import com.ipukr.elephant.dssclient.domain.DPDevice;
import com.ipukr.elephant.dssclient.domain.DPOrganization;
import com.ipukr.elephant.dssclient.domain.res.DPSnapshot;
import com.ipukr.elephant.dssclient.third.DPSSnapshotCallable;
import com.ipukr.elephant.utils.DataUtils;
import com.ipukr.elephant.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2020/4/4.
 */
public class DPUtils {

//	/**
//	 * TODO 逻辑严谨性
//	 * @param dname 设备名称
//	 * @param organization 待解析组织树
//	 * @return
//	 */
//	public static DPDevice getDPDeviceByName(String dname, DPOrganization organization) {
//
//		if(!dname.isEmpty()&&organization!=null&&organization.getDevices()!=null&&!organization.getDevices().getDevices().isEmpty()){
//			// 1.通过名称+组织树->获取当前车辆的所有设备device信息
//			List<DPOrganization.Devices.DeviceX> deviceList = organization.getDevices().getDevices().stream().filter(e -> {
//				if (dname.equals(e.getName())) {
//					return true;
//				} else {
//					return false;
//				}
//			}).collect(Collectors.toList());
//			// 把设备device信息 赋值给DPDevice
//			if(!deviceList.isEmpty()){
//				DPOrganization.Devices.DeviceX deviceX = deviceList.get(0);
//				DPDevice de=new DPDevice();
//				de.setId(deviceX.getId());
//				de.setName(dname);
//				de.setType(deviceX.getType());
//				return de;
//			}
//		}
//		return null;
//	}


	/**
	 *
	 * @param dname 设备名称
	 * @param organization 待解析组织树
	 * @return
	 */
	public static List<DPDevice> getDPDeviceByName(String dname, DPOrganization organization) {

		List<DPDevice> devices = new ArrayList<>();

		if(!dname.isEmpty()&&organization!=null&&organization.getDevices()!=null&&!organization.getDevices().getDevices().isEmpty()){
			// 1.通过名称+组织树->获取当前车辆的所有设备device信息
			devices = organization.getDevices().getDevices().stream().filter(e
					-> dname.equals(e.getName())
			).map(x-> {
				DPDevice device = DataUtils.copyPropertiesIgnoreNull(x, DPDevice.class);
				List<DPChannel> channels = x.getUnitNodes().stream()
						.filter(e->e.getType() == 1)
						.flatMap(m->m.getChannel().stream())
						.map(y -> y.parser())
						.collect(Collectors.toList());
				device.setChannels(channels);
				return device;
			}).collect(Collectors.toList());
		}
		return devices;
	}

	/**
	 *
	 * @param id
	 * @param organization 待解析组织树
	 * @return
	 */
	public static Optional<DPDevice> getDPDeviceById(Integer id, DPOrganization organization) {

		Optional<DPDevice> device = Optional.<DPDevice>of(null);

		if( id!=null &&organization!=null&&organization.getDevices()!=null&&!organization.getDevices().getDevices().isEmpty()){
			// 1.通过名称+组织树->获取当前车辆的所有设备device信息
			device = organization.getDevices().getDevices().stream().filter(e
					-> id.equals(e.getId())
			).map(x-> {
				DPDevice d = DataUtils.copyPropertiesIgnoreNull(x, DPDevice.class);
				List<DPChannel> channels = x.getUnitNodes().stream()
						.filter(e->e.getType() == 1)
						.flatMap(m->m.getChannel().stream())
						.map(y -> y.parser())
						.collect(Collectors.toList());

				d.setChannels(channels);
				return d;
			}).findFirst();

		}
		return device;
	}

	public static List<DPChannel> getChannelByDevice(DPDevice device, DPOrganization organization) {
		return organization.getDevices().getDevices().stream().filter(e -> {
			return e.getId() == device.getId();
		}).flatMap(e-> {
			return e.getUnitNodes().stream();
		}).filter(n-> {
			return n.getType() == 1;
		}).flatMap(m -> m.getChannel().stream()).map(x->x.parser()).collect(Collectors.toList());


	}
}
