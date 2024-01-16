package com.blackdotan.elephant.mail;

import com.blackdotan.elephant.mail.third.SSLMailSender;
import com.blackdotan.elephant.mail.config.SSLMailSenderConfig;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by wmw on 6/2/16.
 */
public class MailSenderTest {
    private MailSender sender;

    /**
     * 配置发件邮箱
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        SSLMailSenderConfig config = SSLMailSenderConfig.custom()
                .host("smtp.exmail.qq.com")
                .port(465)
                .protocol("smtp")
                .auth(true)
                .username("wmw@ipukr.cn")
                .password("F42xSoQZzXTTcuCS")
                .from("【SCRAT】企业级数据中台")
                .build();
        sender = new SSLMailSender(config);
    }

    /**
     * 发送邮件
     * @throws Exception
     */
    @Test
    public void send() throws Exception {
        sender.send("wmw@ipukr.cn", "主题:测试邮件发送","测试邮件发送");
    }

}
