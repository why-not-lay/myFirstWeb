package com.myFirstWeb.controller;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailController {
    private static String mail = "losorin@163.com";
    private static String identify = "ADMNAPBTYZJSHBLK";
    public static void SendMail(String mail_other, String title, String content) {
        //设置服务器参数
        Properties props = new Properties();
        props.setProperty("mail.host", "smtp.163.com");
        props.setProperty("mail.transport.protocol", "SMTP");
        props.setProperty("mail.smtp.auth", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail,identify);
            }
        };

        //实例化session
        Session session = Session.getInstance(props,authenticator);

        //初始化邮件
        MimeMessage mess = new MimeMessage(session);
        try {
            //设置邮件各项信息
            mess.setFrom(new InternetAddress(mail));
            mess.setRecipients(Message.RecipientType.TO, mail_other);
            mess.setSubject(title);
            mess.setContent(content,"text/html;charset=utf-8");
            //发送邮件
            Transport.send(mess);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
