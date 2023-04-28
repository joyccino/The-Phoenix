package com.phoenix.qpproject.service;

import com.phoenix.qpproject.dto.MailDTO;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    private JavaMailSender emailSender;

    public void sendPassResetEmail(MailDTO mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("info.thephoenixclub@gmail.com");
        message.setTo(mailDto.getAddress());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getContent());
        emailSender.send(message);
    }
}
