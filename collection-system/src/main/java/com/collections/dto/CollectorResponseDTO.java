package com.collections.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectorResponseDTO {

    private Long collectorId;
    private String name;
    private String email;
    private String phone;
    private String region;
    private String status;
}