package com.ipukr.elephant.push.backend;

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
import com.ipukr.elephant.push.IPush;
import com.ipukr.elephant.push.config.GetuiIMPushConfig;
import com.ipukr.elephant.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ryan on 下午10:43.
 */

public class GetuiIMPush implements IPush{

    private static final Logger logger = LoggerFactory.getLogger(GetuiIMPush.class);

    private GetuiIMPushConfig config;

    private IGtPush mIGtPush;


    public GetuiIMPush(GetuiIMPushConfig config) {
        this.config = config;
        this.init();
    }

    /**
     * 初始化方法
     */
    private void init() {
        logger.debug("初始化组件 {}, config={}", GetuiIMPush.class.getCanonicalName(), config.toString());
        mIGtPush = new IGtPush(config.getAppkey(), config.getMaster());
    }





    @Override
    public boolean push(String CID, String title, String text) {
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



        // 对单个用户推送消息
        ListMessage message = new ListMessage();
        message.setData(template);
        message.setOffline(config.getOffline());
        message.setOfflineExpireTime(24 * 1000 * 3600);

        List targets = new ArrayList();
        Target target = new Target();
        target.setAppId(config.getAppid());
        target.setClientId(CID);
        targets.add(target);

        String taskId = mIGtPush.getContentId(message);
        IPushResult mIPushResult = mIGtPush.pushMessageToList(taskId, targets);;
        logger.debug("推送任务 taskId={}, result={}", taskId, JsonUtils.parserObj2String(mIPushResult));
        return mIPushResult.getResponse().get("result").equals("ok");
    }

    @Override
    public boolean push(List<String> CIDs, String title, String text) {
        logger.debug("开始向Client_Ids:{}, 推送数据text:{}", CIDs, text);

        NotificationTemplate template = generatedNotificationTemplate(title, text);

        ListMessage message = new ListMessage();
        message.setData(template);
        message.setOffline(config.getOffline());
        message.setOfflineExpireTime(24 * 1000 * 3600);

        List<Target> targets = new ArrayList<Target>();
        if(!CIDs.isEmpty()) {
            for(String CID : CIDs) {
                Target target = new Target();
                target.setAppId(config.getAppid());
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
    public boolean push(String title, String text) {
        logger.debug("开始向App所有Client, 推送数据text:{}", text);

        NotificationTemplate template = generatedNotificationTemplate(title, text);

        AppMessage message = new AppMessage();
        message.setAppIdList(Arrays.asList(config.getAppid()));
        message.setData(template);
        message.setOffline(config.getOffline());
        message.setOfflineExpireTime(24 * 1000 * 3600);

        IPushResult mIPushResult = mIGtPush.pushMessageToApp(message);
        logger.debug("推送任务 result={}", JsonUtils.parserObj2String(mIPushResult));
        return mIPushResult.getResponse().get("result").equals("ok");
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public List<String> bind(String device, String channel) {
        return null;
    }

    private NotificationTemplate generatedNotificationTemplate(String title, String text) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(config.getAppid());
        template.setAppkey(config.getAppkey());

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

        APNPayload iAPNPayload = new APNPayload();
        APNPayload.DictionaryAlertMsg iAlertMsg = new APNPayload.DictionaryAlertMsg();
        iAlertMsg.setTitle(title);
        iAlertMsg.setBody(text);
        iAlertMsg.setTitleLocKey("TitleLocKey");
        iAlertMsg.addTitleLocArg("TitleLocArg");

        iAPNPayload.setAlertMsg(iAlertMsg);
        iAPNPayload.setSound("default");
        template.setAPNInfo(iAPNPayload);

        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        template.setTransmissionContent(text);

        return template;
    }


}
