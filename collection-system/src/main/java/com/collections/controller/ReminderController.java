package com.collections.controller;

import com.collections.dto.ReminderRequestDTO;
import com.collections.dto.ReminderResponseDTO;
import com.collections.service.ReminderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    @PostMapping
    public ReminderResponseDTO createReminder(@Valid @RequestBody ReminderRequestDTO request) {
        return reminderService.createReminder(request);
    }

    @GetMapping
    public List<ReminderResponseDTO> getAllReminders() {
        return reminderService.getAllReminders();
    }

    @GetMapping("/{id}")
    public ReminderResponseDTO getReminder(@PathVariable Long id) {
        return reminderService.getReminderById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
    }
}