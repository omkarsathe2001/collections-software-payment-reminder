package com.collections.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {

    private Long accountId;

    private String accountNumber;

    private Double loanAmount;

    private Double emiAmount;

    private LocalDate dueDate;

    private String status;

    private Long customerId;

}