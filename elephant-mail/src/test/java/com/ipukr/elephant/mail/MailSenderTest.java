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

    @Before
    public void setUp() throws Exception {
        SSLMailSenderConfig config = SSLMailSenderConfig.custom()
                .host("smtp.exmail.qq.com")
                .port(25)
                .protocol("smtp")
                .auth(true)
                .username("username")
                .password("")
                .build();
        sender = new SSLMailSender(config);
    }

    @Test
    public void tst() throws Exception {
        sender.send("msg@ipukr.cn","wmw@ipukr.cn", "主题:测试邮件发送","测试邮件发送");
    }

}
