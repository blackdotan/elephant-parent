package com.ipukr.elephant.push.backend;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.AbstractTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.context.Context;
import com.ipukr.elephant.push.IPush;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ryan on 下午10:43.
 */
public class GetuiIMPush extends AbstractAPI implements IPush{

    private static final Logger logger = LoggerFactory.getLogger(GetuiIMPush.class);

    public static final String HOST = "host";
    public static final String APPID = "appid";
    public static final String APPKEY = "appkey";
    public static final String MASTER = "master";


    private String host;
    private String appid;
    private String appkey;
    private String master;

    private IGtPush mIGtPush;

    public GetuiIMPush(Context context) {
        super(context);
        this.init();
    }

    public void init(){
        this.host = context.findStringAccordingKey(HOST, "http://sdk.open.api.igexin.com/apiex.htm");
        this.appid = context.findStringAccordingKey(APPID);
        this.appkey = context.findStringAccordingKey(APPKEY);
        this.master = context.findStringAccordingKey(MASTER);
        mIGtPush = new IGtPush(host, appkey, master);
    }

    public boolean push(String CID, String text) {
        logger.debug("开始向Client_Id:{}, 推送数据text:{}", CID, text);
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appid);
        template.setAppkey(appkey);
        template.setTransmissionContent(text);
        // 对单个用户推送消息
        SingleMessage message = new SingleMessage();
        message.setData(template);
        Target target = new Target();
        target.setAppId(appid);
        target.setClientId(CID);
        IPushResult mIPushResult = mIGtPush.pushMessageToSingle(message, target);
        return mIPushResult.getResponse().get("result").equals("ok");
    }

    public boolean push(List<String> CIDs, String text) {
        logger.debug("开始向Client_Ids:{}, 推送数据text:{}", CIDs, text);
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appid);
        template.setAppkey(appkey);
        template.setTransmissionContent(text);

        ListMessage message = new ListMessage();
        message.setData(template);

        List<Target> targets = new ArrayList<Target>();
        if(!CIDs.isEmpty()) {
            for(String CID : CIDs) {
                Target target = new Target();
                target.setAppId(appid);
                target.setClientId(CID);
                targets.add(target);
            }
            String taskId = mIGtPush.getContentId(message);
            IPushResult mIPushResult = mIGtPush.pushMessageToList(taskId, targets);
            return mIPushResult.getResponse().get("result").equals("ok");
        } else {
            logger.warn("推送警告, 你要推送的CIDs为空");
            return true;
        }

    }
}
