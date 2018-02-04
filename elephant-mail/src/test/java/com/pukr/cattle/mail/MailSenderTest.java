package com.pukr.cattle.mail;

import com.google.common.collect.Lists;
import com.ipukr.elephant.architecture.factory.Factory;
import com.pukr.cattle.mail.third.SMTPMailSender;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;

import javax.mail.MessagingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmw on 6/2/16.
 */
public class MailSenderTest {
    private MailSender sender;
    @Test
    public void tst() throws Exception {
        sender = Factory.getInstance().build("mail.properties");
        sender.sender("wmw@ipukr.cn","主题:测试邮件发送","测试邮件发送");
    }

}
