package com.collections.mapper;

import com.collections.dto.CollectorRequestDTO;
import com.collections.dto.CollectorResponseDTO;
import com.collections.entity.Collector;

public class CollectorMapper {

    // DTO -> Entity
    public static Collector toEntity(CollectorRequestDTO dto) {
        return Collector.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .region(dto.getRegion())
                .status(dto.getStatus())
                .build();
    }

    // Entity -> DTO
    public static CollectorResponseDTO toResponse(Collector collector) {
        return CollectorResponseDTO.builder()
                .collectorId(collector.getCollectorId())
                .name(collector.getName())
                .email(collector.getEmail())
                .phone(collector.getPhone())
                .region(collector.getRegion())
                .status(collector.getStatus())
                .build();
    }
}