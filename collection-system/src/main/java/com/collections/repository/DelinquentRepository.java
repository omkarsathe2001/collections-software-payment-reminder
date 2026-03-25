package com.collections.repository;

import com.collections.entity.Collector;
import com.collections.entity.DelinquentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DelinquentRepository extends JpaRepository<DelinquentAccount, Long> {

    Optional<DelinquentAccount> findByAccountAccountId(Long accountId);

    long countByDaysPastDueLessThanEqual(int days);

    long countByDaysPastDueBetween(int start, int end);

    long countByDaysPastDueGreaterThan(int days);

    //Collector Performance data
    long countByCollector(Collector collector);
}