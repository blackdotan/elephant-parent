package com.ipukr.elephant.mail;

import com.ipukr.elephant.mail.config.SSLMailSenderConfig;
import com.ipukr.elephant.mail.third.SSLMailSender;
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
                .port(25)
                .protocol("smtp")
                .auth(true)
                .username("msg@ipukr.cn")
                .password("%H9N8x^7Oi%Nxm6w")
                .from("msg@ipukr.cn")
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
