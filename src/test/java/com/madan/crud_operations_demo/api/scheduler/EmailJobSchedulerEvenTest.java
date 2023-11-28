package com.madan.crud_operations_demo.api.scheduler;

import com.madan.crud_operations_demo.constants.EmailStatus;
import com.madan.crud_operations_demo.entity.Email;
import com.madan.crud_operations_demo.jobs.EmailJobSchedulerEven;
import com.madan.crud_operations_demo.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmailJobSchedulerEvenTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private EmailRepository emailRepository;

    @InjectMocks
    private EmailJobSchedulerEven emailJobSchedulerEven;

    private Email email;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        email = new Email();
        email.setEmailId(2);
        email.setEmailAddress("test@example.com");
        email.setEmailStatus(EmailStatus.CREATED);
    }

    @Test
    void whenEmailNotInWhitelist_thenSendEmail() throws MessagingException {
        List<Email> emails = Collections.singletonList(email);
        List<String> whitelistEmails = List.of("other@example.com");
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(emailRepository.findById(anyInt())).thenReturn(Optional.of(email));

        emailJobSchedulerEven.emailJobExecutionEven(emails, whitelistEmails);

        verify(mailSender).send(mimeMessage);
        verify(emailRepository).save(any(Email.class));
    }

    @Test
    void whenEmailInWhitelist_thenDoNotSendEmail() {
        List<Email> emails = Collections.singletonList(email);
        List<String> whitelistEmails = List.of("test@example.com");

        emailJobSchedulerEven.emailJobExecutionEven(emails, whitelistEmails);

        verify(mailSender, never()).send(any(MimeMessage.class));
        verify(emailRepository, never()).save(any(Email.class));
    }

    @Test
    void whenMessagingException_thenUpdateStatusToFailed() {
        List<Email> emails = Collections.singletonList(email);
        List<String> whitelistEmails = List.of("other@example.com");
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(emailRepository.findById(anyInt())).thenReturn(Optional.of(email));
        doThrow(new MailSendException("Error sending email")).when(mailSender).send(mimeMessage);


        emailJobSchedulerEven.emailJobExecutionEven(emails, whitelistEmails);


        assertEquals(EmailStatus.FAILED, email.getEmailStatus());
    }
}
