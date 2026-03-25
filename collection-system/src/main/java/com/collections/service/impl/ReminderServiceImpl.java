package com.collections.service.impl;

import com.collections.dto.ReminderRequestDTO;
import com.collections.dto.ReminderResponseDTO;
import com.collections.entity.Account;
import com.collections.entity.ReminderLog;
import com.collections.exception.ResourceNotFoundException;
import com.collections.mapper.ReminderMapper;
import com.collections.repository.AccountRepository;
import com.collections.repository.ReminderRepository;
import com.collections.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;
    private final AccountRepository accountRepository;

    @Override
    public ReminderResponseDTO createReminder(ReminderRequestDTO request) {

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        ReminderLog reminder = ReminderMapper.toEntity(request, account);

        ReminderLog saved = reminderRepository.save(reminder);

        return ReminderMapper.toResponseDTO(saved);
    }

    @Override
    public List<ReminderResponseDTO> getAllReminders() {

        return reminderRepository.findAll()
                .stream()
                .map(ReminderMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReminderResponseDTO getReminderById(Long id) {

        ReminderLog reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found"));

        return ReminderMapper.toResponseDTO(reminder);
    }

    @Override
    public void deleteReminder(Long id) {

        reminderRepository.deleteById(id);
    }
}