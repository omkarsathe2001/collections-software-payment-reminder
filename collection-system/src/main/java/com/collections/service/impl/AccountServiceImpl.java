package com.collections.service.impl;

import com.collections.dto.AccountRequestDTO;
import com.collections.dto.AccountResponseDTO;
import com.collections.entity.Account;
import com.collections.entity.Customer;
import com.collections.exception.ResourceNotFoundException;
import com.collections.mapper.AccountMapper;
import com.collections.repository.*;
import com.collections.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final DelinquentRepository delinquentRepository;
    private final PaymentRepository paymentRepository;
    private final ReminderRepository reminderRepository;

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Account account = AccountMapper.toEntity(request, customer);

        Account savedAccount = accountRepository.save(account);

        return AccountMapper.toResponseDTO(savedAccount);
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {

        return accountRepository.findAll()
                .stream()
                .map(AccountMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getAccountById(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        return AccountMapper.toResponseDTO(account);
    }

    @Override
    public AccountResponseDTO updateAccount(Long id, AccountRequestDTO request) {

        Account existing = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        existing.setAccountNumber(request.getAccountNumber());
        existing.setLoanAmount(request.getLoanAmount());
        existing.setEmiAmount(request.getEmiAmount());
        existing.setDueDate(request.getDueDate());
        existing.setStatus(request.getStatus());

        Account updated = accountRepository.save(existing);

        return AccountMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteAccount(Long id) {

        // delete child records first
        delinquentRepository.deleteById(id);
        paymentRepository.deleteById(id);
        reminderRepository.deleteById(id);

        //then delete account
        accountRepository.deleteById(id);
    }
}