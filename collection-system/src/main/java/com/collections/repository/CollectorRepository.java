package com.collections.repository;

import com.collections.entity.Collector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectorRepository extends JpaRepository<Collector, Long> {
}
