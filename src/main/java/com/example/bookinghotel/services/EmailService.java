package com.example.bookinghotel.services;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import java.util.Properties;


public interface EmailService {

    public void sendAMessage(String to, String subject, String text);
    public void sendAHTMLMessage(String to, String subject, String link,String name) throws MessagingException;
}