package com.teenpatti.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
    
    @KafkaListener(topics = "game-notifications", groupId = "notification-group")
    public void handleGameNotification(String message) {
        // Process game notifications and send emails/push notifications
    }
    
    @KafkaListener(topics = "transaction-notifications", groupId = "notification-group")
    public void handleTransactionNotification(String message) {
        // Process transaction notifications and send emails/push notifications
    }
}