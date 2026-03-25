package com.collections.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reminder_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReminderLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reminderId;

    private String reminderType;

    private LocalDate reminderDate;

    private String message;

    private Integer dpd;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}