package com.madan.crud_operations_demo.scheduler.jobs;

import com.madan.crud_operations_demo.constants.EmailStatus;
import com.madan.crud_operations_demo.email.EmailDetails;
import com.madan.crud_operations_demo.entity.Email;
import com.madan.crud_operations_demo.repository.EmailRepository;
import com.madan.crud_operations_demo.service.EmailJobService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmailJobServiceImpl implements EmailJobService {
    private final EmailRepository emailRepository;

    private final JavaMailSender mailSender;

    @Value("${whitelist.emails}")
    private String[] whiteList;

    public EmailJobServiceImpl(EmailRepository emailRepository,
                               JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }

    public void addEmployeeToEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("emailDress cannot be Null /Empty");
        }
        Email newemail = new Email();
        newemail.setEmailAddress(email);
        newemail.setEmailStatus(EmailStatus.CREATED);
        emailRepository.save(newemail);
    }

    private void updateEmailStatus(int emailId, EmailStatus status) {
        Optional<Email> optionalEmail = emailRepository.findById(emailId);
        if (optionalEmail.isPresent()) {
            Email newEmail = optionalEmail.get();
            newEmail.setEmailStatus(status);
            emailRepository.save(newEmail);
        }

    }

    private EmailDetails hardCodedValuesForEvenId() {
        EmailDetails dummyDetails = new EmailDetails();
        dummyDetails.setSubject("Test Email For Even Email ID");
        dummyDetails.setBody("Test Email from Springboot Application");
        return dummyDetails;
    }

    private EmailDetails hardCodedValuesForOddId() {
        EmailDetails dummyDetails = new EmailDetails();
        dummyDetails.setSubject("Test Email For Odd Email ID");
        dummyDetails.setBody("Test Email from Springboot Application");
        return dummyDetails;
    }


    private void emailJobExecution(List<Email> emails, List<String> whitelistEmails, EmailDetails emailDetails) {
        for (Email email : emails) {
            String emailAddress = email.getEmailAddress();
            if (!whitelistEmails.contains(emailAddress.toLowerCase())) {
                try {
                    MimeMessage mimeMessage = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

                    helper.setTo(emailAddress);
                    helper.setSubject(emailDetails.getSubject());
                    helper.setText(emailDetails.getBody());

                    updateEmailStatus(email.getEmailId(), EmailStatus.SENT);

                    mailSender.send(mimeMessage);
                } catch (MessagingException e) {
                    updateEmailStatus(email.getEmailId(), EmailStatus.FAILED);
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Scheduled(cron = "${email.cron.expression}")
    private void emailSchedular() {
        List<Email> emails = emailRepository.findAll();
        List<String> whitelistEmails = Arrays.stream(whiteList).map(String::toLowerCase).toList();
        for (Email email : emails) {
            int emailId = email.getEmailId();
            if (emailId % 2 == 0) {
                EmailDetails emailDetails = hardCodedValuesForEvenId();
                emailJobExecution(emails, whitelistEmails, emailDetails);
            } else {
                EmailDetails emailDetails = hardCodedValuesForOddId();
                emailJobExecution(emails, whitelistEmails, emailDetails);
            }
        }
    }
}