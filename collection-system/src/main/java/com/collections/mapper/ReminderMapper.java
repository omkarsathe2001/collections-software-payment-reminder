package com.collections.mapper;

import com.collections.dto.ReminderRequestDTO;
import com.collections.dto.ReminderResponseDTO;
import com.collections.entity.Account;
import com.collections.entity.ReminderLog;

public class ReminderMapper {

    public static ReminderLog toEntity(ReminderRequestDTO dto, Account account) {

        ReminderLog reminder = new ReminderLog();

        reminder.setReminderType(dto.getReminderType());
        reminder.setReminderDate(dto.getReminderDate());
        reminder.setMessage(dto.getMessage());
        reminder.setAccount(account);

        return reminder;
    }

    public static ReminderResponseDTO toResponseDTO(ReminderLog reminder) {

        ReminderResponseDTO dto = new ReminderResponseDTO();

        dto.setReminderId(reminder.getReminderId());
        dto.setReminderType(reminder.getReminderType());
        dto.setReminderDate(reminder.getReminderDate());
        dto.setMessage(reminder.getMessage());
        dto.setAccountId(reminder.getAccount().getAccountId());

        return dto;
    }
}