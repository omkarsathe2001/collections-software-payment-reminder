package com.collections.notification;

import org.springframework.stereotype.Service;

@Service
public class SmsService {

    public void sendSMS(String phone, String message) {

        System.out.println("SMS sent to: " + phone);
        System.out.println("Message: " + message);
    }
}
