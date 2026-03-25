package com.collections.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {

    private Long paymentId;

    private Double amount;

    private LocalDate paymentDate;

    private String paymentMethod;

    private String status;

    private Long accountId;
}