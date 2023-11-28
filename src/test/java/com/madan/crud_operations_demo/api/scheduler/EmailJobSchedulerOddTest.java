package com.madan.crud_operations_demo.api.scheduler;

import com.madan.crud_operations_demo.constants.EmailStatus;
import com.madan.crud_operations_demo.entity.Email;
import com.madan.crud_operations_demo.jobs.EmailJobSchedulerOdd;
import com.madan.crud_operations_demo.repository.EmailRepository;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailJobSchedulerOddTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private EmailRepository emailRepository;

    @InjectMocks
    private EmailJobSchedulerOdd emailJobSchedulerOdd;

    private Email email;

    @BeforeEach
    void setUp() {
        email = new Email();
        email.setEmailId(1);
        email.setEmailAddress("oddtest@example.com");
        email.setEmailStatus(EmailStatus.CREATED);
    }

    @Test
    void whenEmailNotInWhitelist_thenSendEmail() {
        List<Email> emails = Collections.singletonList(email);
        List<String> whitelistEmails = Arrays.asList("other@example.com");
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(emailRepository.findById(anyInt())).thenReturn(Optional.of(email));

        emailJobSchedulerOdd.emailJobExecutionOdd(emails, whitelistEmails);

        verify(mailSender).send(mimeMessage);
        verify(emailRepository).save(any(Email.class));
    }

    @Test
    void whenEmailInWhitelist_thenDoNotSendEmail() {
        List<Email> emails = Arrays.asList(email);
        List<String> whitelistEmails = Arrays.asList("oddtest@example.com");

        emailJobSchedulerOdd.emailJobExecutionOdd(emails, whitelistEmails);

        verify(mailSender, never()).send(any(MimeMessage.class));
        verify(emailRepository, never()).save(any(Email.class));
    }

    @Test
    void whenMessagingException_thenUpdateStatusToFailed() {
        // Arrange
        List<Email> emails = Arrays.asList(email);
        List<String> whitelistEmails = Arrays.asList("other@example.com");
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(emailRepository.findById(anyInt())).thenReturn(Optional.of(email));
        doThrow(new MailSendException("Error sending email")).when(mailSender).send(any(MimeMessage.class));


        emailJobSchedulerOdd.emailJobExecutionOdd(emails, whitelistEmails);


        assertEquals(EmailStatus.FAILED, email.getEmailStatus());
    }


}
