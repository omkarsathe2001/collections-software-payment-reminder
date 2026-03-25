package com.collections.service;

import com.collections.dto.AccountRequestDTO;
import com.collections.dto.AccountResponseDTO;

import java.util.List;

public interface AccountService {

    AccountResponseDTO createAccount(AccountRequestDTO request);

    List<AccountResponseDTO> getAllAccounts();

    AccountResponseDTO getAccountById(Long id);

    AccountResponseDTO updateAccount(Long id, AccountRequestDTO request);

    void deleteAccount(Long id);
}