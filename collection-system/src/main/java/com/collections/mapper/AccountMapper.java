package com.collections.mapper;

import com.collections.dto.AccountRequestDTO;
import com.collections.dto.AccountResponseDTO;
import com.collections.entity.Account;
import com.collections.entity.Customer;

public class AccountMapper {

    public static Account toEntity(AccountRequestDTO dto, Customer customer) {

        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber());
        account.setLoanAmount(dto.getLoanAmount());
        account.setEmiAmount(dto.getEmiAmount());
        account.setDueDate(dto.getDueDate());
        account.setStatus(dto.getStatus());
        account.setCustomer(customer);

        return account;
    }

    public static AccountResponseDTO toResponseDTO(Account account) {

        AccountResponseDTO dto = new AccountResponseDTO();

        dto.setAccountId(account.getAccountId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setLoanAmount(account.getLoanAmount());
        dto.setEmiAmount(account.getEmiAmount());
        dto.setDueDate(account.getDueDate());
        dto.setStatus(account.getStatus());
        dto.setCustomerId(account.getCustomer().getCustomerId());

        return dto;
    }
}