package com.collections.mapper;

import com.collections.dto.DelinquentRequestDTO;
import com.collections.dto.DelinquentResponseDTO;
import com.collections.entity.Account;
import com.collections.entity.Collector;
import com.collections.entity.DelinquentAccount;

public class DelinquentMapper {

    public static DelinquentAccount toEntity(DelinquentRequestDTO dto,
                                             Account account,
                                             Collector collector) {

        DelinquentAccount delinquent = new DelinquentAccount();

        delinquent.setDaysPastDue(dto.getDaysPastDue());
        delinquent.setLastReminderDate(dto.getLastReminderDate());
        delinquent.setStatus(dto.getStatus());
        delinquent.setAccount(account);
        delinquent.setCollector(collector);

        return delinquent;
    }

    public static DelinquentResponseDTO toResponseDTO(DelinquentAccount delinquent) {

        DelinquentResponseDTO dto = new DelinquentResponseDTO();

        dto.setDelinquentId(delinquent.getDelinquentId());
        dto.setDaysPastDue(delinquent.getDaysPastDue());
        dto.setLastReminderDate(delinquent.getLastReminderDate());
        dto.setStatus(delinquent.getStatus());
        dto.setAccountId(delinquent.getAccount().getAccountId());

        // ✅ Collector
        if (delinquent.getCollector() != null) {
            dto.setCollectorName(delinquent.getCollector().getName());
        }

        // 🔥 IMPORTANT JOIN LOGIC
        if (delinquent.getAccount() != null) {

            dto.setAccountNumber(delinquent.getAccount().getAccountNumber());

            if (delinquent.getAccount().getCustomer() != null) {
                dto.setCustomerName(
                        delinquent.getAccount().getCustomer().getName()
                );
            }
        }

        return dto;
    }
}