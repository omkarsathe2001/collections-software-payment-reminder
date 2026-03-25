package com.collections.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "collectors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Collector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collectorId;

    private String name;

    private String email;

    private String phone;

    private String region;

    private String status;
}