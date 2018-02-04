package com.ipukr.elephant.http.third;

import com.ipukr.elephant.architecture.factory.Factory;
import com.ipukr.elephant.http.third.utils.PCMHelper;
import com.ipukr.elephant.utils.DateUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/29.
 */
public class HttpClientPoolTest {
    @Test
    public void testName() throws Exception {
        HttpClientPool pool = Factory.getInstance().build("config.properties");
        HttpClient client = pool.getConnection();

        File file = new File(this.getClass().getResource("/").getPath().concat("data/test.pcm"));
        byte[] bytes = IOUtils.toByteArray(new FileInputStream(file));


        for (int i = 0; i < 10; i++) {

            InputStreamEntity entity  = new InputStreamEntity(new ByteArrayInputStream(bytes), -1);

            URI URI = new URIBuilder()
                    .setScheme("https")
                    .setHost("dictation.nuancemobility.net")
                    .setPort(443)
                    .setPath("/NMDPAsrCmdServlet/dictation")
                    .addParameter("appId", "HTTP_NMDPPRODUCTION_Le_Trans_Letransi_20170731035035")
                    .addParameter("appKey","2f7ae4a6da8735d80ea6d18c679bbcec3df6b7067af30ad9f700558da20e96d1897a776301ac3c0f520d9bbaa958a6e64e5decc851f7a248f7f7d599066b6a8f")
                    .addParameter("id","00000000000000000000000000000001")
                    .setCharset(Consts.UTF_8)
                    .build();

            HttpUriRequest request = RequestBuilder.post(URI)
                    .setCharset(Consts.UTF_8)
                    .addHeader("Accept-Language", "cmn-CHN")
                    .addHeader("Accept-Topic", "WebSearch")
                    .addHeader("Accept", "text/plain;charset=utf-8")
                    .addHeader("Content-Type", "audio/x-wav;codec=pcm;bit=16;rate=16000")
                    .setEntity(entity)
                    .build();

            Date begin = DateUtils.now();
            HttpResponse response = client.execute(request);
            Date end = DateUtils.now();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            StringBuffer buffer = new StringBuffer();
            String sentence = null;
            while(( sentence = reader.readLine() ) != null){
                buffer.append(sentence);
            }

            System.out.println(buffer.toString()+", consume="+ (end.getTime() - begin.getTime()));
        }
    }

    @Test
    public void testName2() throws Exception {

        HttpClientPool pool = HttpClientPool.custome()
                .schema("https")
                .hostname("dictation.nuancemobility.net")
                .port((short) 443)
                .protocol("TLSv1,TLSv1.1,TLSv1.2")
                .timeout(60000)
                .proxyHostname("45.77.198.207")
                .proxyPort((short) 3128)
                .proxyUsername("wmw1612")
                .proxyPassword("kb0bta63")
                .connections(200)
                .routeMax(50)
                .dns("dictation.nuancemobility.net:205.197.192.118")
                .build();

        HttpClient client = pool.getConnection();

        File file = new File(this.getClass().getResource("/").getPath().concat("data/test.pcm"));
        byte[] bytes = IOUtils.toByteArray(new FileInputStream(file));


        for (int i = 0; i < 10; i++) {

            InputStreamEntity entity  = new InputStreamEntity(new ByteArrayInputStream(bytes), -1);

            URI URI = new URIBuilder()
                    .setScheme("https")
                    .setHost("dictation.nuancemobility.net")
                    .setPort(443)
                    .setPath("/NMDPAsrCmdServlet/dictation")
                    .addParameter("appId", "HTTP_NMDPPRODUCTION_Le_Trans_Letransi_20170731035035")
                    .addParameter("appKey","2f7ae4a6da8735d80ea6d18c679bbcec3df6b7067af30ad9f700558da20e96d1897a776301ac3c0f520d9bbaa958a6e64e5decc851f7a248f7f7d599066b6a8f")
                    .addParameter("id","00000000000000000000000000000001")
                    .setCharset(Consts.UTF_8)
                    .build();

            HttpUriRequest request = RequestBuilder.post(URI)
                    .setCharset(Consts.UTF_8)
                    .addHeader("Accept-Language", "cmn-CHN")
                    .addHeader("Accept-Topic", "WebSearch")
                    .addHeader("Accept", "text/plain;charset=utf-8")
                    .addHeader("Content-Type", "audio/x-wav;codec=pcm;bit=16;rate=16000")
                    .setEntity(entity)
                    .build();

            Date begin = DateUtils.now();
            HttpResponse response = client.execute(request);
            Date end = DateUtils.now();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            StringBuffer buffer = new StringBuffer();
            String sentence = null;
            while(( sentence = reader.readLine() ) != null){
                buffer.append(sentence);
            }

            System.out.println(buffer.toString()+", consume="+ (end.getTime() - begin.getTime()));
        }
    }

    @Test
    public void testName3() throws Exception {
        HttpClientPool pool = Factory.getInstance().build("config.properties");
        HttpClient client = pool.getConnection();

        HttpEntity entity = new StringEntity("汉堡包一个多少钱", "UTF-8");;

        URI URI = new URIBuilder()
                .setScheme("https")
                .setHost("tts.nuancemobility.net")
                .setPort(443)
                .setPath("/NMDPTTSCmdServlet/tts")
                .addParameter("appId", "HTTP_NMDPPRODUCTION_Le_Trans_Letransi_20170731035035")
                .addParameter("appKey", "2f7ae4a6da8735d80ea6d18c679bbcec3df6b7067af30ad9f700558da20e96d1897a776301ac3c0f520d9bbaa958a6e64e5decc851f7a248f7f7d599066b6a8f")
                .addParameter("id", "00000000000000000000000000000001")
                .addParameter("ttsLang", "cmn-CHN")
                .setCharset(Consts.UTF_8)
                .build();

        HttpUriRequest request = RequestBuilder.post(URI)
                .setCharset(Consts.UTF_8)
                .addHeader("Accept-Language", "cmn-CHN")
                .addHeader("Content-Type", "text/plain;charset=utf-8")
                .addHeader("Accept", "audio/x-wav;codec=pcm;bit=16;rate=16000")
                .addParameter("ttsLang", "cmn-CHN")
                .setEntity(entity)
                .build();

        Date begin = DateUtils.now();
        HttpResponse response = client.execute(request);
        Date end = DateUtils.now();

        System.out.println(response.getStatusLine().getStatusCode()+"," + response.getStatusLine().getReasonPhrase());
        DataInputStream in = new DataInputStream(response.getEntity().getContent());
        File file2 = new File(this.getClass().getResource("/").getPath().concat("data/test2.wav"));
        byte[] bytes2 = PCMHelper.PCM2Wave(IOUtils.toByteArray(in), 1, 16000, 16);
        FileUtils.writeByteArrayToFile(file2, bytes2);


    }
}
