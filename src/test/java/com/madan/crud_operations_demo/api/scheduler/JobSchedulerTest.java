package com.madan.crud_operations_demo.api.scheduler;

import com.madan.crud_operations_demo.entity.Email;
import com.madan.crud_operations_demo.jobs.EmailJobSchedulerEven;
import com.madan.crud_operations_demo.jobs.EmailJobSchedulerOdd;
import com.madan.crud_operations_demo.repository.EmailRepository;
import com.madan.crud_operations_demo.scheduler.JobsScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JobsSchedulerTest {

    @Mock
    private EmailRepository emailRepository;
    @Mock
    private EmailJobSchedulerEven emailJobSchedulerEven;
    @Mock
    private EmailJobSchedulerOdd emailJobSchedulerOdd;

    @InjectMocks
    private JobsScheduler jobsScheduler;

    private Email evenEmail, oddEmail;
    private List<String> whitelistEmails;

    @BeforeEach
    void setUp() {
        evenEmail = new Email();
        evenEmail.setEmailId(2); // Assume this is an even ID email
        // Set up the rest of the evenEmail details

        oddEmail = new Email();
        oddEmail.setEmailId(3); // Assume this is an odd ID email
        // Set up the rest of the oddEmail details

        whitelistEmails = Arrays.asList("whitelist@example.com");
        ReflectionTestUtils.setField(jobsScheduler, "whiteList", new String[]{"whitelist@example.com"});
    }

    @Test
    void emailScheduler_shouldCallAppropriateSchedulersBasedOnEmailId() {
        List<Email> emails = Arrays.asList(evenEmail, oddEmail);
        when(emailRepository.findAll()).thenReturn(emails);

        jobsScheduler.emailScheduler();

        verify(emailJobSchedulerEven).emailJobExecutionEven(emails, whitelistEmails);
        verify(emailJobSchedulerOdd).emailJobExecutionOdd(emails, whitelistEmails);
    }
}

