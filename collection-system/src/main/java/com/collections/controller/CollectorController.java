package com.collections.controller;

import com.collections.dto.CollectorRequestDTO;
import com.collections.dto.CollectorResponseDTO;
import com.collections.service.CollectorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collectors")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CollectorController {

    private final CollectorService service;

    // ✅ CREATE COLLECTOR
    @PostMapping
    public CollectorResponseDTO createCollector(
            @Valid @RequestBody CollectorRequestDTO dto) {

        return service.createCollector(dto);
    }

    // ✅ GET ALL COLLECTORS
    @GetMapping
    public List<CollectorResponseDTO> getAllCollectors() {

        return service.getAllCollectors();
    }

    // ✅ GET COLLECTOR BY ID
    @GetMapping("/{id}")
    public CollectorResponseDTO getCollectorById(
            @PathVariable Long id) {

        return service.getCollectorById(id);
    }

    // ✅ UPDATE COLLECTOR
    @PutMapping("/{id}")
    public CollectorResponseDTO updateCollector(
            @PathVariable Long id,
            @Valid @RequestBody CollectorRequestDTO dto) {

        return service.updateCollector(id, dto);
    }

    // ✅ DELETE COLLECTOR
    @DeleteMapping("/{id}")
    public String deleteCollector(
            @PathVariable Long id) {

        service.deleteCollector(id);
        return "Collector deleted successfully";
    }
}