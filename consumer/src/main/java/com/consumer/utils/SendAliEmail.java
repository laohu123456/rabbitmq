package com.consumer.utils;



import com.server.pojo.MainInfo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class SendAliEmail {

    private final static String EMAIL_ADDRESS = "192.168.56.106";

    public static void sendMail(MainInfo mainInfo) throws MessagingException, UnsupportedEncodingException {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.protocol", "smtp");
        properties.setProperty("mail.smtp.host", EMAIL_ADDRESS);
        properties.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mainInfo.getSendEmailAccount(), mainInfo.getSendEmailPassword());
            }
        });
        session.setDebug(true);

        MimeMessage mimeMessage = createMimeMessage(session, mainInfo);

        Transport transport = session.getTransport();

        transport.connect();

        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

        transport.close();

    }

    public static MimeMessage createMimeMessage(Session session, MainInfo mainInfo) throws UnsupportedEncodingException, MessagingException {
        MimeMessage mimeMessage = new MimeMessage(session);

        mimeMessage.addFrom(new InternetAddress[]{new InternetAddress(mainInfo.getSendEmailAccount(), mainInfo.getSendPersonName(), "UTF-8")});
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO,
            new InternetAddress(mainInfo.getReceiveMailAccount(), mainInfo.getReceivePersonName(), "UTF-8"));
        mimeMessage.setSubject(mainInfo.getMailTitle(), "UTF-8");
        mimeMessage.setContent(mainInfo.getMailContent(), "text/html;charset=UTF-8");
        mimeMessage.setSentDate(new Date());
        mimeMessage.saveChanges();
        return mimeMessage;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
        MainInfo mainInfo = new MainInfo();
        mainInfo.setSendEmailAccount("h@hyq.a");
        mainInfo.setSendEmailPassword("h");
        mainInfo.setReceiveMailAccount("y@hyq.a");
        mainInfo.setSendPersonName("H");
        mainInfo.setReceivePersonName("Y");
        mainInfo.setMailTitle("firestEmail");
        mainInfo.setMailContent("这是第一封Java发送的邮件");
        sendMail(mainInfo);
    }

}
