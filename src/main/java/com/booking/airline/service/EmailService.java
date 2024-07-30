package com.booking.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("shraddga@26gmail.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setText(body);
        mailSender.send(simpleMailMessage);
    }
}

