package com.collections.service;

import com.collections.dto.DelinquentRequestDTO;
import com.collections.dto.DelinquentResponseDTO;

import java.util.List;

public interface DelinquentService {

    DelinquentResponseDTO createDelinquent(DelinquentRequestDTO request);

    List<DelinquentResponseDTO> getAllDelinquents();

    DelinquentResponseDTO getDelinquentById(Long id);

    void deleteDelinquent(Long id);

    void assignCollector(Long delinquentId, Long collectorId);

    void removeCollector(Long delinquentId);
}