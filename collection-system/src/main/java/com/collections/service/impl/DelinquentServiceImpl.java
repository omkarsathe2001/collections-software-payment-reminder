package com.collections.service.impl;

import com.collections.dto.DelinquentRequestDTO;
import com.collections.dto.DelinquentResponseDTO;
import com.collections.entity.Account;
import com.collections.entity.Collector;
import com.collections.entity.DelinquentAccount;
import com.collections.exception.ResourceNotFoundException;
import com.collections.mapper.DelinquentMapper;
import com.collections.repository.AccountRepository;
import com.collections.repository.CollectorRepository;
import com.collections.repository.DelinquentRepository;
import com.collections.service.DelinquentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DelinquentServiceImpl implements DelinquentService {

    private final DelinquentRepository delinquentRepository;
    private final AccountRepository accountRepository;
    private final CollectorRepository collectorRepository;

    @Override
    public DelinquentResponseDTO createDelinquent(DelinquentRequestDTO request) {

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        Collector collector = null;

        if(request.getCollectorId() != null){
            collector = collectorRepository.findById(request.getCollectorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Collector not found"));
        }

        DelinquentAccount delinquent =
                DelinquentMapper.toEntity(request, account, collector);

        DelinquentAccount saved =
                delinquentRepository.save(delinquent);

        return DelinquentMapper.toResponseDTO(saved);
    }

    @Override
    public List<DelinquentResponseDTO> getAllDelinquents() {

        return delinquentRepository.findAll()
                .stream()
                .map(DelinquentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DelinquentResponseDTO getDelinquentById(Long id) {

        DelinquentAccount delinquent =
                delinquentRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Delinquent account not found"));

        return DelinquentMapper.toResponseDTO(delinquent);
    }

    @Override
    public void deleteDelinquent(Long id) {

        delinquentRepository.deleteById(id);
    }

    @Override
    public void assignCollector(Long delinquentId, Long collectorId) {

        DelinquentAccount delinquent = delinquentRepository.findById(delinquentId)
                .orElseThrow(() -> new ResourceNotFoundException("Delinquent not found"));

        Collector collector = collectorRepository.findById(collectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Collector not found"));

        delinquent.setCollector(collector);

        delinquentRepository.save(delinquent);
    }

    @Override
    public void removeCollector(Long delinquentId) {

        DelinquentAccount delinquent = delinquentRepository.findById(delinquentId)
                .orElseThrow(() -> new ResourceNotFoundException("Delinquent not found"));

        delinquent.setCollector(null); // 🔥 REMOVE COLLECTOR

        delinquentRepository.save(delinquent);
    }
}