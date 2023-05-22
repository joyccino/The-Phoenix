package com.phoenix.qpproject.service;

import com.phoenix.qpproject.dto.MailDTO;
import com.phoenix.qpproject.mail.EmailUtil;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
@AllArgsConstructor
public class EmailService {
    private JavaMailSender emailSender;

    public void sendPassResetEmail(MailDTO mailDto) throws jakarta.mail.MessagingException {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("info.thephoenixclub@gmail.com");
//        message.setTo(mailDto.getAddress());
//        message.setSubject(mailDto.getTitle());
//        message.setText(mailDto.getContent());
//        emailSender.send(message);
        System.out.println("sentPassResetEmail 시작");
        jakarta.mail.internet.MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = "<h3>Hello World!<h3>";
        helper.setText(htmlMsg, true);
        helper.setFrom("info.thephoenixclub@gmail.com");
        helper.setTo(mailDto.getAddress());
        helper.setSubject(mailDto.getTitle());
        emailSender.send(mimeMessage);
        System.out.println("sentPassResetEmail done");

    }

    public void sendPassResetEmailHtml(MailDTO mailDto){
        final String fromEmail = "info.thephoenixclub@gmail.com"; //requires valid gmail id
        final String password = "amdvyyhiuwkucikc"; // correct password for gmail id
        //final String toEmail = "joy2moon4u@gmail.com"; // can be any email id
        final String toEmail = mailDto.getAddress(); // can be any email id

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
        sendEmail(session, toEmail, mailDto.getTitle(), mailDto.getContent());
    }

    public void setNewPassword(MailDTO mailDto){
        String newPassword = mailDto.getContent();
        String theme = mailDto.getTheme();
        mailDto.setContent(theme.replace("tempPassword",newPassword));
    }

    public static void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "ThePhoenixClub"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "text/html;UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("이메일 전송 완료!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
