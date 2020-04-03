package com.ipukr.elephant.dssclient;

import com.ipukr.elephant.dssclient.config.DPSClientConfig;
import com.ipukr.elephant.dssclient.domain.DPChannel;
import com.ipukr.elephant.dssclient.domain.DPDevice;
import com.ipukr.elephant.dssclient.domain.DPOrganization;
import com.ipukr.elephant.dssclient.domain.res.DPSnapshot;
import com.ipukr.elephant.dssclient.third.DPSClient;
import com.ipukr.elephant.utils.JsonUtils;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import java.io.FileOutputStream;
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
		//1.登陆
		Client client = new DPSClient(config);
		//2.获取组织树
		DPOrganization iDPOrganization = client.group();
		if(iDPOrganization==null){
			return;
		}
		//3.通过车名+组织树 ->获取设备id+name+type(暂时不知道怎么用)
		DPDevice dpDevice = client.getDPDevice("105-闽DQ1G09", iDPOrganization);
		//4.通过设备id->获取所有图片信息DPChannel(通道id+对应序号0,1,2.. 车辆详情名称name 类型type)对象 + data(base64)图片数据
		List<DPSnapshot> snapshotList = client.snapshot(dpDevice);
        //5.通过图片信息DPChannel(通道id)-> 获取实时视频流
        FileOutputStream ous=new FileOutputStream("D:\\1.mp4");
		try{
            DPSnapshot dpSnapshot = snapshotList.get(0);//图片信息
            DPChannel channel = dpSnapshot.getChannel();
            Integer integer = client.getReal(channel, ous);
            channel.setNsequence(integer);
            try {
                Thread.sleep(120000);
            }catch (Exception e){
                ous.close();
                e.printStackTrace();
            }
            //6.通过channel中的nsequence 去关闭视频流
            client.closeReal(channel);
        }catch (Exception e){
            ous.close();
            e.printStackTrace();
        }


		//7.登出
		client.logout();
		//8.清除缓存
		client.destroy();

		/*System.out.println(JsonUtils.parserObj2String(iDPOrganization));
		DPOrganization.Devices.DeviceX dx = iDPOrganization.getDevices().getDevices().stream().filter(e->e.getName().matches(".*243.*")).findFirst().get();

		// 抓取设备快照
		DPDevice dpd = DPDevice.builder()
				.id(dx.getId())
				.name(dx.getName())
				.build();
		List<DPSnapshot> snapshots = client.snapshot(dpd);*/
	}
}
