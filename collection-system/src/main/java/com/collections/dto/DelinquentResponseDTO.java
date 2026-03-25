package com.collections.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelinquentResponseDTO {

    private Long delinquentId;

    private Integer daysPastDue;

    private LocalDate lastReminderDate;

    private String collectorName;

    private String status;

    private Long accountId;

    // ✅ ADD THESE
    private String accountNumber;
    private String customerName;
}