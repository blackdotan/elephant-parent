package com.ipukr.elephant.mail;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Created by wmw on 12/30/16.
 */
public interface MailSender {

    /**
     * 发送邮件
     * @param from 发件人
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @throws MessagingException 邮件异常
     */
    public void send(String from, String to, String subject, String content) throws MessagingException;

    /**
     * 发送邮件
     * @param from 发件人
     * @param tos 收件人s
     * @param subject 主题
     * @param content 内容
     * @throws MessagingException 邮件异常
     */
    public void send(String from, List<String> tos, String subject, String content) throws MessagingException;

    /**
     * 发送邮件/从配置获取发件人
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @throws MessagingException
     */
    public void send(String to, String subject, String content) throws MessagingException;

    /**
     * 发送邮件/从配置获取发件人
     * @param tos 收件人s
     * @param subject 主题
     * @param content 内容
     * @throws MessagingException 邮件异常
     */
    public void send(List<String> tos, String subject, String content) throws MessagingException;
}
