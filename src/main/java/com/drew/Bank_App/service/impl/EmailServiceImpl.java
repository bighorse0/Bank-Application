package com.drew.Bank_App.service.impl;

import com.drew.Bank_App.dto.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements  EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Override
    public void sendEmail(EmailDetails emailDetails) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderEmail);
            message.setTo(emailDetails.getReceiver());
            message.setSubject(emailDetails.getSubject());
            message.setText(emailDetails.getMessageBody());

            mailSender.send(message);
            System.out.println("Email sent successfully");
        } catch(MailException ex) {
            throw new RuntimeException(ex);
        }
    }

}
