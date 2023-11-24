package com.madan.crud_operations_demo.scheduler;

import com.madan.crud_operations_demo.entity.Email;
import com.madan.crud_operations_demo.jobs.EmailJobSchedulerEven;
import com.madan.crud_operations_demo.jobs.EmailJobSchedulerOdd;
import com.madan.crud_operations_demo.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class JobsScheduler {
    private final EmailRepository emailRepository;

    private final EmailJobSchedulerEven emailJobSchedulerEven;

    private final EmailJobSchedulerOdd emailJobSchedulerOdd;

    @Value("${whitelist.emails}")
    private String[] whiteList;

    public JobsScheduler(EmailRepository emailRepository,
                         EmailJobSchedulerEven emailJobSchedulerEven,
                         EmailJobSchedulerOdd emailJobSchedulerOdd) {
        this.emailRepository = emailRepository;
        this.emailJobSchedulerEven = emailJobSchedulerEven;
        this.emailJobSchedulerOdd = emailJobSchedulerOdd;
    }

    @Scheduled(cron = "${email.cron.expression}")
    public void emailScheduler() {
        List<Email> emails = emailRepository.findAll();
        List<String> whitelistEmails = Arrays.stream(whiteList).map(String::toLowerCase).toList();
        for (Email email : emails) {
            int emailId = email.getEmailId();
            if (emailId % 2 == 0) {
                emailJobSchedulerEven.emailJobExecutionEven(emails, whitelistEmails);
            } else {
                emailJobSchedulerOdd.emailJobExecutionOdd(emails, whitelistEmails);
            }
        }
    }
}
