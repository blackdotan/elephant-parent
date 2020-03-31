package com.ipukr.test;

import com.ipukr.elephant.dssclient.Client;
import com.ipukr.elephant.dssclient.config.DPSClientConfig;
import com.ipukr.elephant.dssclient.domain.DPDevice;
import com.ipukr.elephant.dssclient.domain.DPOrganization;
import com.ipukr.elephant.dssclient.domain.res.DPSnapshot;
import com.ipukr.elephant.dssclient.third.DPSClient;
import com.ipukr.elephant.utils.JsonUtils;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 请描述类 <br>
 *
 * @author ljx
 * <p>
 * Created by ljx wu on 2020-03-31 15:42
 */
public class TestDDS {
    @Test
    public void testDSS() throws IOException {
        DPSClientConfig cc=new DPSClientConfig();
        Client c = new DPSClient(cc);
        DPOrganization iDPOrganization = c.group();

        System.out.println(JsonUtils.parserObj2String(iDPOrganization));
        OutputStream ous = new FileOutputStream("D:\\1.mp4");

        try{
            DPDevice s=new DPDevice();
            s.setId(1000023);
             c.snapshot(s);
//            Thread.sleep(5000);
//            c.snapshot("1000128","0");//拉取图片
//            OutputStream ous = new FileOutputStream("D:\\1.mp4");
//            Integer nRealSeq = c.getReal("1000129$1$0$1", ous);//获取实时码流
//            Thread.sleep(60000);
//            if( nRealSeq > 0) {
//                c.closeReal(nRealSeq);
//            }
            ous.flush();
            ous.close();
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                c.logout();
            } catch (Exception e){
                c.destroy();
            }
        }


    }
}
