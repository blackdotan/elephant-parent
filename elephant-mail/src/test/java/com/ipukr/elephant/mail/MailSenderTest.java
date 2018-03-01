package com.ipukr.elephant.mail;

import com.ipukr.elephant.architecture.factory.Factory;
import org.junit.Test;

/**
 * Created by wmw on 6/2/16.
 */
public class MailSenderTest {
    private MailSender sender;
    @Test
    public void tst() throws Exception {
        sender = Factory.getInstance().build("mail.properties");
        sender.send("wmw@ipukr.cn","主题:测试邮件发送","测试邮件发送");
    }

}
