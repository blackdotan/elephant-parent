package com.blackdotan.elephant.mail.third;

import com.blackdotan.elephant.mail.MailSender;
import com.blackdotan.elephant.mail.config.SSLMailSenderConfig;
import com.blackdotan.elephant.mail.constanst.MailConstanst;
import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by wmw on 6/2/16.
 */
public class SSLMailSender implements MailSender {

    private static final Logger logger = LoggerFactory.getLogger(SSLMailSender.class);

    private SSLMailSenderConfig config;

    private String host;
    private Integer port;
    private String protocol;
    private Boolean auth;
    private String username;
    private String password;
    private String from;
    private Boolean debug;

    private Session session;

    public SSLMailSender(SSLMailSenderConfig config) {
        this.config = config;
        this.init();
    }

    private void init() {
        logger.debug("初始化组件 {}, config={}", SSLMailSender.class.getCanonicalName(), config.toString());
        host  = config.getHost();
        port = config.getPort();
        protocol = config.getProtocol();
        auth = config.getAuth();
        username = config.getUsername();
        password = config.getPassword();
        from = config.getFrom();
        debug = config.getDebug();

        String from = config.getFrom();

        Properties prop = new Properties();
        prop.put(MailConstanst.MAIL_HOST, host);
        prop.put(MailConstanst.MAIL_PORT, port);
        prop.put(MailConstanst.MAIL_TRANSPORT_PROTOCOL, protocol );
        prop.put(MailConstanst.MAIL_SMTP_AUTH, auth );
        prop.put(MailConstanst.MAIL_USERNAME, username );
        prop.put(MailConstanst.MAIL_PASSWORD, password );
        prop.put(MailConstanst.MAIL_FROM, from );



        /**
         * 使用SSL，企业邮箱必需！
         * 开启安全协议
         * */
        if (auth) {
            MailSSLSocketFactory sf = null;
            try {
                sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);
                prop.put("mail.smtp.ssl.enable", "true");
                prop.put("mail.smtp.ssl.socketFactory", sf);

                prop.put("mail.smtp.starttls.enable", "true");
                prop.put("mail.smtp.starttls.required", "true");
                prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

            } catch (GeneralSecurityException e1) {
                e1.printStackTrace();
            }
        }


        session = Session.getInstance(prop);
        session.setDebug(debug);
    }

    public void send(List<String> tos, List<String> ccs, String subject, String content) throws MessagingException, UnsupportedEncodingException {
        Transport ts = session.getTransport();
        ts.connect(host, username, password);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username, from, "UTF-8"));
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
    public void send(String to, String subject, String content) throws MessagingException, UnsupportedEncodingException {
        send(Arrays.asList(to),null ,subject,content);
    }

    @Override
    public void send(List<String> tos, String subject, String content) throws MessagingException, UnsupportedEncodingException {
        send(tos, null, subject, content);
    }


}
