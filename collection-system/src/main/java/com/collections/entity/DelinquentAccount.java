package com.collections.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "delinquent_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DelinquentAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long delinquentId;

    private Integer daysPastDue;        // DPD

    private LocalDate overdueDate;      // when account became overdue

    private LocalDate lastReminderDate; // last reminder sent date

    @ManyToOne
    @JoinColumn(name = "collector_id", nullable = true)
    private Collector collector;

    private String status;              // PENDING / PAID / LEGAL

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}