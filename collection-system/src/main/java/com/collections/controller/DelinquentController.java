package com.collections.controller;

import com.collections.dto.DelinquentRequestDTO;
import com.collections.dto.DelinquentResponseDTO;
import com.collections.service.DelinquentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delinquents")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DelinquentController {

    private final DelinquentService delinquentService;

    @PostMapping
    public DelinquentResponseDTO createDelinquent(
            @Valid @RequestBody DelinquentRequestDTO request) {

        return delinquentService.createDelinquent(request);
    }

    @GetMapping
    public List<DelinquentResponseDTO> getAllDelinquents() {

        return delinquentService.getAllDelinquents();
    }

    @GetMapping("/{id}")
    public DelinquentResponseDTO getDelinquent(
            @PathVariable Long id) {

        return delinquentService.getDelinquentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDelinquent(
            @PathVariable Long id) {

        delinquentService.deleteDelinquent(id);
    }

    @PutMapping("/assign/{delinquentId}/{collectorId}")
    public String assignCollector(
            @PathVariable Long delinquentId,
            @PathVariable Long collectorId) {

        delinquentService.assignCollector(delinquentId, collectorId);

        return "Collector Assigned Successfully";
    }

    @PutMapping("/remove/{delinquentId}")
    public String removeCollector(@PathVariable Long delinquentId) {

        delinquentService.removeCollector(delinquentId);

        return "Collector Removed Successfully";
    }
}