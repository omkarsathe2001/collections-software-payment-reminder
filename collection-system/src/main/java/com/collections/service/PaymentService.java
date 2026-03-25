package com.collections.service;

import com.collections.dto.PaymentRequestDTO;
import com.collections.dto.PaymentResponseDTO;

import java.util.List;

public interface PaymentService {

    PaymentResponseDTO createPayment(PaymentRequestDTO request);

    List<PaymentResponseDTO> getAllPayments();

    PaymentResponseDTO getPaymentById(Long id);

    PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO request);

    void deletePayment(Long id);
}