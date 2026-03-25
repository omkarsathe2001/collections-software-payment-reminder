package com.collections.service.impl;

import com.collections.dto.CollectorRequestDTO;
import com.collections.dto.CollectorResponseDTO;
import com.collections.entity.Collector;
import com.collections.exception.ResourceNotFoundException;
import com.collections.mapper.CollectorMapper;
import com.collections.repository.CollectorRepository;
import com.collections.service.CollectorService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectorServiceImpl implements CollectorService {

    private final CollectorRepository repository;

    @Override
    public CollectorResponseDTO createCollector(CollectorRequestDTO dto) {

        Collector collector = CollectorMapper.toEntity(dto);

        Collector saved = repository.save(collector);

        return CollectorMapper.toResponse(saved);
    }

    @Override
    public List<CollectorResponseDTO> getAllCollectors() {

        return repository.findAll()
                .stream()
                .map(CollectorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CollectorResponseDTO getCollectorById(Long id) {

        Collector collector = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collector not found"));

        return CollectorMapper.toResponse(collector);
    }

    @Override
    public CollectorResponseDTO updateCollector(Long id, CollectorRequestDTO dto) {

        Collector collector = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collector not found"));

        collector.setName(dto.getName());
        collector.setEmail(dto.getEmail());
        collector.setPhone(dto.getPhone());
        collector.setRegion(dto.getRegion());
        collector.setStatus(dto.getStatus());

        Collector updated = repository.save(collector);

        return CollectorMapper.toResponse(updated);
    }

    @Override
    public void deleteCollector(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Collector not found");
        }

        repository.deleteById(id);
    }
}