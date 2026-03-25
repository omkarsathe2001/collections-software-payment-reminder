package com.collections.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {

    @NotBlank(message = "Account number is required")
    private String accountNumber;

    @NotNull(message = "Loan amount is required")
    @Positive(message = "Loan amount must be positive")
    private Double loanAmount;

    @NotNull(message = "EMI amount is required")
    @Positive(message = "EMI must be positive")
    private Double emiAmount;

    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date must be today or future")
    private LocalDate dueDate;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Customer ID is required")
    private Long customerId;
}