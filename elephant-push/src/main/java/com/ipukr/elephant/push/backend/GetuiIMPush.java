package com.ipukr.elephant.push.backend;

import com.beust.jcommander.internal.Lists;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
import com.gexin.rp.sdk.template.AbstractTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.context.Context;
import com.ipukr.elephant.push.IPush;
import com.ipukr.elephant.utils.JsonUtils;
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
    public static final String OFFLINE = "notification.offline";
    public static final String TITLE = "notification.title";
    public static final String SOUND = "notification.sound";


    private String host;
    private String appid;
    private String appkey;
    private String master;
    private Boolean offline;
    private String title;
    private String sound;
    private IGtPush mIGtPush;

    public GetuiIMPush(Context context) {
        super(context);
        this.host = context.findStringAccordingKey(HOST, "http://sdk.open.api.igexin.com/apiex.htm");
        this.appid = context.findStringAccordingKey(APPID);
        this.appkey = context.findStringAccordingKey(APPKEY);
        this.master = context.findStringAccordingKey(MASTER);
        this.offline = context.findBooleanAccordingKey(OFFLINE);
        this.title = context.findStringAccordingKey(TITLE, "应用通知");
        this.sound = context.findStringAccordingKey(SOUND, "default");
        this.init();
    }

    private GetuiIMPush(Builder builder) {
        super(null);
        host = builder.host;
        appid = builder.appid;
        appkey = builder.appkey;
        master = builder.master;
    }

    public void init(){
        mIGtPush = new IGtPush(host, appkey, master);
    }

    @Override
    public boolean push(String CID, String text) {
        logger.debug("开始向Client_Id:{}, 推送数据text:{}", CID, text);
//        TransmissionTemplate template = new TransmissionTemplate();
//        template.setAppId(appid);
//        template.setAppkey(appkey);
//        template.setTransmissionContent(text);

//        APNPayload iAPNPayload = new APNPayload();
//        iAPNPayload.setAlertMsg(new APNPayload.SimpleAlertMsg(text));
//
//        APNTemplate template = new APNTemplate();
//        template.setAppId(appid);
//        template.setAppkey(appkey);
//        template.setAPNInfo(iAPNPayload);

        NotificationTemplate template = generatedNotificationTemplate(title, text);
//        template.setAppId(appid);
//        template.setAppkey(appkey);
//        template.setAPNInfo(iAPNPayload);
//        template.setTransmissionContent(text);


        // 对单个用户推送消息
        ListMessage message = new ListMessage();
        message.setData(template);
        message.setOffline(offline);
        message.setOfflineExpireTime(24 * 1000 * 3600);

        List targets = new ArrayList();
        Target target = new Target();
        target.setAppId(appid);
        target.setClientId(CID);
        targets.add(target);

        String taskId = mIGtPush.getContentId(message);
        IPushResult mIPushResult = mIGtPush.pushMessageToList(taskId, targets);;
        logger.debug("推送任务 taskId={}, result={}", taskId, JsonUtils.parserObj2String(mIPushResult));
        return mIPushResult.getResponse().get("result").equals("ok");
    }

    @Override
    public boolean push(List<String> CIDs, String text) {
        logger.debug("开始向Client_Ids:{}, 推送数据text:{}", CIDs, text);

        NotificationTemplate template = generatedNotificationTemplate(title, text);

        ListMessage message = new ListMessage();
        message.setData(template);
        message.setOffline(offline);
        message.setOfflineExpireTime(24 * 1000 * 3600);

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
            logger.debug("推送任务 taskId={}, result={}", taskId, JsonUtils.parserObj2String(mIPushResult));
            return mIPushResult.getResponse().get("result").equals("ok");
        } else {
            logger.warn("推送警告, 你要推送的CIDs为空");
            return true;
        }
    }

    @Override
    public boolean push(String text) {
        logger.debug("开始向App所有Client, 推送数据text:{}", text);

        NotificationTemplate template = generatedNotificationTemplate(title, text);

        AppMessage message = new AppMessage();
        message.setAppIdList(Lists.newArrayList(appid));
        message.setData(template);
        message.setOffline(offline);
        message.setOfflineExpireTime(24 * 1000 * 3600);

        IPushResult mIPushResult = mIGtPush.pushMessageToApp(message);
        logger.debug("推送任务 result={}", JsonUtils.parserObj2String(mIPushResult));
        return mIPushResult.getResponse().get("result").equals("ok");
    }

    private NotificationTemplate generatedNotificationTemplate(String title, String text) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appid);
        //
        template.setAppkey(appkey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(text);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

//        APNPayload iAPNPayload = new APNPayload();
//        APNPayload.DictionaryAlertMsg iAlertMsg = new APNPayload.DictionaryAlertMsg();
//        iAlertMsg.setTitle(title);
//        iAlertMsg.setBody(text);
//        iAlertMsg.setTitleLocKey("TitleLocKey");
//        iAlertMsg.addTitleLocArg("TitleLocArg");
//
//        iAPNPayload.setAlertMsg(iAlertMsg);
//        iAPNPayload.setSound(sound);
//        template.setAPNInfo(iAPNPayload);


        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        template.setTransmissionContent(text);

        return template;
    }

    public final static Builder custom() {
        return new Builder();
    }

    public static final class Builder {
        private String host = "http://sdk.open.api.igexin.com/apiex.htm";
        private String appid;
        private String appkey;
        private String master;

        public Builder() {
        }

        public Builder host(String val) {
            host = val;
            return this;
        }

        public Builder appid(String val) {
            appid = val;
            return this;
        }

        public Builder appkey(String val) {
            appkey = val;
            return this;
        }

        public Builder master(String val) {
            master = val;
            return this;
        }

        public GetuiIMPush build() {
            return new GetuiIMPush(this);
        }
    }
}
