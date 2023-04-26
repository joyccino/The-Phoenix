package com.phoenix.qpproject.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SSLEmail {

    /**
     * Outgoing Mail (SMTP) Server
     * requires TLS or SSL: smtp.gmail.com (use authentication)
     * Use Authentication: Yes
     * Port for SSL: 465
     */
    public static void main(String[] args) {
        final String fromEmail = "info.thephoenixclub@gmail.com"; //requires valid gmail id
        final String password = "mnizxoanckvxeqyp"; // correct password for gmail id
        //final String toEmail = "joy2moon4u@gmail.com"; // can be any email id
        final String toEmail = "sbins402@naver.com"; // can be any email id

        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Session created");
        EmailUtil.sendEmail(session, toEmail, "큐피 비밀번호 찾기", "새로 생성된 비밀번호를 여기 삽입할 예정.");

        //EmailUtil.sendAttachmentEmail(session, toEmail,"SSLEmail Testing Subject with Attachment", "SSLEmail Testing Body with Attachment");

        //EmailUtil.sendImageEmail(session, toEmail,"SSLEmail Testing Subject with Image", "SSLEmail Testing Body with Image");

    }
}
