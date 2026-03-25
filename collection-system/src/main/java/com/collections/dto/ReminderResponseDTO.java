package com.collections.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReminderResponseDTO {

    private Long reminderId;

    private String reminderType;

    private LocalDate reminderDate;

    private String message;

    private Long accountId;
}