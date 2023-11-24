package com.madan.crud_operations_demo.jobs;

import com.madan.crud_operations_demo.constants.EmailStatus;
import com.madan.crud_operations_demo.entity.Email;
import com.madan.crud_operations_demo.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component

public class EmailJobSchedulerOdd {
    private final JavaMailSender mailSender;

    private final EmailRepository emailRepository;

    public EmailJobSchedulerOdd(JavaMailSender mailSender,
                                EmailRepository emailRepository) {
        this.mailSender = mailSender;
        this.emailRepository = emailRepository;
    }

    private void updateEmailStatus(int emailId, EmailStatus status) {
        Optional<Email> optionalEmail = emailRepository.findById(emailId);
        if (optionalEmail.isPresent()) {
            Email newEmail = optionalEmail.get();
            newEmail.setEmailStatus(status);
            emailRepository.save(newEmail);
        }
    }

    public void emailJobExecutionOdd(List<Email> emails, List<String> whitelistEmails) {
        for (Email email : emails) {
            String emailAddress = email.getEmailAddress();
            if (!whitelistEmails.contains(emailAddress.toLowerCase())) {
                try {
                    MimeMessage mimeMessage = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

                    helper.setTo(emailAddress);
                    helper.setSubject("This Email is for ODD ID");
                    helper.setText("This is a test Email for ODD ID");
                    mailSender.send(mimeMessage);
                    updateEmailStatus(email.getEmailId(), EmailStatus.SENT);
                } catch (MessagingException e) {
                    updateEmailStatus(email.getEmailId(), EmailStatus.FAILED);
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
