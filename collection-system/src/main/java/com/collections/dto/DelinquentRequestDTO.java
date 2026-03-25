package com.collections.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DelinquentRequestDTO {

    @NotNull(message = "Days past due is required")
    @Min(value = 0, message = "DPD cannot be negative")
    private Integer daysPastDue;

    private LocalDate lastReminderDate;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Account ID is required")
    private Long accountId;

    private Long collectorId;
}