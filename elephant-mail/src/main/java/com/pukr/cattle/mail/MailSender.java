package com.pukr.cattle.mail;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Created by wmw on 12/30/16.
 */
public interface MailSender {

    public void sender(String from, String to, String subject, String content) throws MessagingException;

    public void sender(String from, List<String> tos, String subject, String content) throws MessagingException;

    public void sender(String to, String subject, String content) throws MessagingException;

    public void sender(List<String> tos, String subject, String content) throws MessagingException;
}
