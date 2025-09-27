package com.code.craft.by.shubham.codecraft.by.shubham.service.IMPL;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendEmail(String to, String subject, String body){

        try {
            // Implementation for sending email
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // Set to true for HTML content
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            e.getMessage();
            throw new RuntimeException(e);
        }


    }
}

