package com.ipukr.elephant.mail.third;

import com.google.common.collect.Lists;

import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.context.Context;
import com.ipukr.elephant.mail.MailSender;
import com.ipukr.elephant.mail.constanst.MailConstanst;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;

/**
 * Created by wmw on 6/2/16.
 */
public class SSLMailSender extends AbstractAPI implements MailSender{


    private String host;
    private String protocol;
    private String auth;
    private String username;
    private String password;
    private Boolean debug;

    private Session session;

    public SSLMailSender(Context context) {
        super(context);
        this.init();
    }

    public void init(){
        host = context.findStringAccordingKey(MailConstanst.MAIL_HOST);
        protocol = context.findStringAccordingKey(MailConstanst.MAIL_TRANSPORT_PROTOCOL);
        auth = context.findStringAccordingKey(MailConstanst.MAIL_SMTP_AUTH);
        username = context.findStringAccordingKey(MailConstanst.MAIL_USERNAME);
        password = context.findStringAccordingKey(MailConstanst.MAIL_PASSWORD);
        debug = (Boolean) context.findBooleanAccordingKey(MailConstanst.MAIL_DEBUG, false);

        Properties prop = new Properties();
        prop.put(MailConstanst.MAIL_HOST, host );
        prop.put(MailConstanst.MAIL_TRANSPORT_PROTOCOL, protocol );
        prop.put(MailConstanst.MAIL_SMTP_AUTH, auth );
        prop.put(MailConstanst.MAIL_USERNAME, username );
        prop.put(MailConstanst.MAIL_PASSWORD, password );

        /**
         * 使用SSL，企业邮箱必需！
         * 开启安全协议
         * */
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);


        session = Session.getInstance(prop);
        session.setDebug(debug);
    }

    public void send(String from, List<String> tos, List<String> ccs, String subject, String content) throws MessagingException {
        Transport ts = session.getTransport();
        ts.connect(host, username, password);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        if(tos==null || tos.size()==0){
            throw new RuntimeException("Error While Ent MimeMessage Tos is null or empty");
        }
        Address[] to_addresses = new Address[tos.size()];
        for(int i=0;i<to_addresses.length;i++){
            to_addresses[i] = new InternetAddress(tos.get(i));
        }
        message.setRecipients(Message.RecipientType.TO, to_addresses);
        if(ccs!=null && ccs.size()>0) {
            Address[] cc_addresses = new Address[ccs.size()];
            for (int i = 0; i < cc_addresses.length; i++) {
                cc_addresses[i] = new InternetAddress(ccs.get(i));
            }
            message.setRecipients(Message.RecipientType.CC, cc_addresses);
        }
        message.setSubject(subject);
        message.setContent(content, "text/html;charset=UTF-8");

        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }



    @Override
    public void send(String from, String to, String subject, String content) throws MessagingException {
        send(from, Lists.newArrayList(to),null ,subject,content);
    }

    @Override
    public void send(String from, List<String> tos, String subject, String content) throws MessagingException {
        send(from, tos, null, subject, content);
    }

    @Override
    public void send(String to, String subject, String content) throws MessagingException {
        String from = context.findStringAccordingKey(MailConstanst.MAIL_FROM);
        send(from, Lists.newArrayList(to),null ,subject,content);
    }

    @Override
    public void send(List<String> tos, String subject, String content) throws MessagingException {
        String from = context.findStringAccordingKey(MailConstanst.MAIL_FROM);
        send(from, tos, null, subject, content);
    }
}
