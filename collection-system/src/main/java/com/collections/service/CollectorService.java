package com.collections.service;

import com.collections.dto.CollectorRequestDTO;
import com.collections.dto.CollectorResponseDTO;

import java.util.List;

public interface CollectorService {

    CollectorResponseDTO createCollector(CollectorRequestDTO dto);

    List<CollectorResponseDTO> getAllCollectors();

    CollectorResponseDTO getCollectorById(Long id);

    CollectorResponseDTO updateCollector(Long id, CollectorRequestDTO dto);

    void deleteCollector(Long id);
}