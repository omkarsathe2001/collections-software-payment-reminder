package com.collections.mapper;

import com.collections.dto.PaymentRequestDTO;
import com.collections.dto.PaymentResponseDTO;
import com.collections.entity.Account;
import com.collections.entity.Payment;

public class PaymentMapper {

    public static Payment toEntity(PaymentRequestDTO dto, Account account) {

        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setStatus(dto.getStatus());
        payment.setAccount(account);

        return payment;
    }

    public static PaymentResponseDTO toResponseDTO(Payment payment) {

        PaymentResponseDTO dto = new PaymentResponseDTO();

        dto.setPaymentId(payment.getPaymentId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setStatus(payment.getStatus());
        dto.setAccountId(payment.getAccount().getAccountId());

        return dto;
    }
}