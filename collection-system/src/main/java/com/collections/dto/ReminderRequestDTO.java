package com.collections.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReminderRequestDTO {

    @NotBlank(message = "Reminder type is required")
    private String reminderType;

    @NotNull(message = "Reminder date is required")
    private LocalDate reminderDate;

    @Size(max = 300, message = "Message too long")
    private String message;

    @NotNull(message = "Account ID is required")
    private Long accountId;
}