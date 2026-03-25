package com.collections.service;

import com.collections.dto.ReminderRequestDTO;
import com.collections.dto.ReminderResponseDTO;

import java.util.List;

public interface ReminderService {

    ReminderResponseDTO createReminder(ReminderRequestDTO request);

    List<ReminderResponseDTO> getAllReminders();

    ReminderResponseDTO getReminderById(Long id);

    void deleteReminder(Long id);
}