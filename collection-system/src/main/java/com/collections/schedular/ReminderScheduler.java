package com.collections.schedular;

import com.collections.emailservice.EmailService;
import com.collections.entity.Account;
import com.collections.entity.Collector;
import com.collections.entity.DelinquentAccount;
import com.collections.entity.ReminderLog;
import com.collections.repository.AccountRepository;
import com.collections.repository.CollectorRepository;
import com.collections.repository.DelinquentRepository;
import com.collections.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReminderScheduler {

    private final AccountRepository accountRepository;
    private final ReminderRepository reminderRepository;
    private final DelinquentRepository delinquentRepository;
    private final CollectorRepository collectorRepository;
    private final EmailService emailService;

    // Round Robin counter
    private int collectorIndex = 0;

    @Scheduled(cron = "0 0 9 * * ?")   // Runs every day at 9 AM
//    @Scheduled(fixedRate = 20000)
    public void checkOverdueAccounts() {

        List<Account> accounts = accountRepository.findAll();
        List<Collector> collectors = collectorRepository.findAll();

        for (Account account : accounts) {

            if (account.getDueDate().isBefore(LocalDate.now())
                    && account.getStatus().equals("ACTIVE")) {

                long dpd = ChronoUnit.DAYS.between(account.getDueDate(), LocalDate.now());

                account.setStatus("OVERDUE");
                accountRepository.save(account);

                DelinquentAccount delinquent =
                        delinquentRepository.findByAccountAccountId(account.getAccountId())
                                .orElse(new DelinquentAccount());

                delinquent.setAccount(account);
                delinquent.setDaysPastDue((int) dpd);
                delinquent.setOverdueDate(account.getDueDate());

                String stage;

                // 🔥 STAGE LOGIC
                if (dpd <= 30) {
                    stage = "REMINDER";
                } else if (dpd <= 60) {
                    stage = "COLLECTOR_STAGE";

                    if (delinquent.getCollector() == null && !collectors.isEmpty()) {
                        Collector assigned = collectors.get(collectorIndex);
                        delinquent.setCollector(assigned);

                        collectorIndex = (collectorIndex + 1) % collectors.size();
                    }
                } else {
                    stage = "LEGAL_STAGE";
                }

                delinquent.setStatus(stage);

                // 🔥 PREVENT DUPLICATE LOG FOR SAME STAGE SAME DAY
                boolean alreadyLoggedToday =
                        delinquent.getLastReminderDate() != null &&
                                delinquent.getLastReminderDate().equals(LocalDate.now());

                if (!alreadyLoggedToday) {

                    String message;
                    String type;

                    switch (stage) {

                        case "REMINDER":
                            message = "Reminder: Your EMI is overdue. Please pay immediately.";
                            type = "EMAIL";
                            break;

                        case "COLLECTOR_STAGE":
                            Collector c = delinquent.getCollector();
                            if (c != null) {
                                message = "Collector " + c.getName() +
                                        " assigned for recovery.";
                            } else {
                                message = "Collector will be assigned soon.";
                            }
                            type = "CALL";
                            break;

                        case "LEGAL_STAGE":
                            message = "Legal notice initiated due to non-payment.";
                            type = "EMAIL";
                            break;

                        default:
                            message = "EMI overdue.";
                            type = "EMAIL";
                    }

                    // 🔥 SAVE LOG
                    ReminderLog log = ReminderLog.builder()
                            .account(account)
                            .reminderType(type)
                            .message(message)
                            .reminderDate(LocalDate.now())
                            .dpd((int) dpd) // 🔥 NEW
                            .build();

                    reminderRepository.save(log);

                    delinquent.setLastReminderDate(LocalDate.now());

                    // 🔥 SEND EMAIL
                    emailService.sendReminderEmail(
                            account.getCustomer().getEmail(),
                            account.getAccountNumber(),
                            dpd
                    );

                    System.out.println("✅ Log + Email sent for: " + account.getAccountNumber());
                }

                delinquentRepository.save(delinquent);
            }
        }
    }
}