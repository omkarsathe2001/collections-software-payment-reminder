package com.collections.repository;

import com.collections.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    long countByStatus(String status);
}