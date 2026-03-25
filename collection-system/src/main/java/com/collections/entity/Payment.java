package com.collections.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Double amount;

    private LocalDate paymentDate;

    private String paymentMethod;

    private String status;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}