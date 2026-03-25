package com.collections.service.impl;

import com.collections.dto.PaymentRequestDTO;
import com.collections.dto.PaymentResponseDTO;
import com.collections.entity.Account;
import com.collections.entity.Payment;
import com.collections.exception.ResourceNotFoundException;
import com.collections.mapper.PaymentMapper;
import com.collections.repository.AccountRepository;
import com.collections.repository.PaymentRepository;
import com.collections.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;

    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO request) {

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        Payment payment = PaymentMapper.toEntity(request, account);

        Payment saved = paymentRepository.save(payment);

        return PaymentMapper.toResponseDTO(saved);
    }

    @Override
    public List<PaymentResponseDTO> getAllPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(PaymentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDTO getPaymentById(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        return PaymentMapper.toResponseDTO(payment);
    }

    @Override
    public PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO request) {

        Payment existing = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        existing.setAmount(request.getAmount());
        existing.setPaymentDate(request.getPaymentDate());
        existing.setPaymentMethod(request.getPaymentMethod());
        existing.setStatus(request.getStatus());

        Payment updated = paymentRepository.save(existing);

        return PaymentMapper.toResponseDTO(updated);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}