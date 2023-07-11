package com.oncha.oncha_web.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailingService {

    private final MailSender mailSender;
    private final String sender;

    public MailingService(MailSender mailSender, @Value("${mail.google.email}") String email) {
        sender = email;
        this.mailSender = mailSender;
    }

    @Async
    public void sendErrorMessage (String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(sender);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
