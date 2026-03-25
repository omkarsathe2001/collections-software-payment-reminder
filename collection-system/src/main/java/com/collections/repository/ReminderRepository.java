package com.collections.repository;

import com.collections.entity.ReminderLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<ReminderLog, Long> {
}