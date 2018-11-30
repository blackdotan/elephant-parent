package com.ipukr.elephant.mail;

import com.ipukr.elephant.mail.config.SSLMailSenderConfig;
import com.ipukr.elephant.mail.third.SSLMailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wmw on 6/2/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SSLMailSenderConfig.class, SSLMailSender.class})
@EnableConfigurationProperties
public class MailSenderTest {
    @Autowired
    private MailSender sender;

    @Test
    public void tst() throws Exception {
        sender.send("msg@ipukr.cn","wmw@ipukr.cn", "主题:测试邮件发送","测试邮件发送");
    }

}
