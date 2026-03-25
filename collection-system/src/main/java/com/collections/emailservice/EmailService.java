package com.collections.emailservice;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendReminderEmail(String email, String accountNumber, long dpd) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Loan EMI Overdue Reminder");

        message.setText(
                "Dear Customer,\n\n" +
                        "Your EMI for account " + accountNumber +
                        " is overdue by " + dpd + " days.\n" +
                        "Please pay immediately.\n\n" +
                        "Collections Department"
        );

        mailSender.send(message);
    }
}