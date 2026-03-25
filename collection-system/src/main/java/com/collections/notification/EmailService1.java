package com.collections.notification;

import org.springframework.stereotype.Service;

@Service
public class EmailService1 {

    public void sendReminderEmail(String email, String message) {

        System.out.println("Sending reminder email to: " + email);
        System.out.println("Message: " + message);
    }
}