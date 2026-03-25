package com.collections.controller;

import com.collections.dto.CollectorPerformanceDTO;
import com.collections.repository.AccountRepository;
import com.collections.repository.CustomerRepository;
import com.collections.repository.DelinquentRepository;
import com.collections.repository.CollectorRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardController {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final DelinquentRepository delinquentRepository;
    private final CollectorRepository collectorRepository; // ✅ ADDED

    @GetMapping
    public Map<String, Object> getDashboardStats() {

        Map<String, Object> map = new HashMap<>();

        long customers = customerRepository.count();
        long accounts = accountRepository.count();
        long overdue = accountRepository.countByStatus("OVERDUE");
        long collectors = collectorRepository.count();

        long dpd30 = delinquentRepository.countByDaysPastDueLessThanEqual(30);
        long dpd60 = delinquentRepository.countByDaysPastDueBetween(31, 60);
        long dpd90 = delinquentRepository.countByDaysPastDueGreaterThan(60);

        // 🔥 PERFORMANCE LOGIC
        List<CollectorPerformanceDTO> performance =
                collectorRepository.findAll()
                        .stream()
                        .map(c -> new CollectorPerformanceDTO(
                                c.getName(),
                                delinquentRepository.countByCollector(c)
                        ))
                        .collect(Collectors.toList());

        map.put("customers", customers);
        map.put("accounts", accounts);
        map.put("overdue", overdue);
        map.put("collectors", collectors);
        map.put("dpd30", dpd30);
        map.put("dpd60", dpd60);
        map.put("dpd90", dpd90);
        map.put("collectorPerformance", performance); // ✅ IMPORTANT

        return map;
    }
}