package com.madan.crud_operations_demo.service;

import com.madan.crud_operations_demo.email.EmailDetails;
import com.madan.crud_operations_demo.entity.Email;
import com.madan.crud_operations_demo.repository.EmailRepository;
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
public class EmailService {

    private final EmailRepository emailRepository;

    private final JavaMailSender mailSender;

    @Value("${whitelist.emails}")
    private String[] whiteList;

    public EmailService(EmailRepository emailRepository,
                        JavaMailSender mailSender){
        this.emailRepository=emailRepository;
        this.mailSender = mailSender;
    }

    public void addEmployeeToEmail(String email) {
        if(email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("emailDress cannot be Null /Empty");
        }
            Email newemail = new Email();
            newemail.setEmailAddress(email);
            newemail.setEmailStatus(Email.EmailStatus.CREATED);
            emailRepository.save(newemail);
    }

    public void updateEmailStatus(int emailId, Email.EmailStatus status){
        Optional<Email> optionalEmail = emailRepository.findById(emailId);
        if (optionalEmail.isPresent()){
            Email newEmail = optionalEmail.get();
            newEmail.setEmailStatus(status);
            emailRepository.save(newEmail);
        }

    }

    private EmailDetails hardCodedValues() {
        EmailDetails dummyDetails = new EmailDetails();
        dummyDetails.setSubject("Test Email");
        dummyDetails.setBody("Test Email from Springboot Application");
        return dummyDetails;
    }


    @Scheduled(cron = "${email.cron.expression}")
    public void emailScheduler() {
        List<Email> toEmailId = emailRepository.findAll();
        System.out.println(toEmailId);
        EmailDetails emailDetails = hardCodedValues();
        List<String> whitelistEmails = Arrays.asList(whiteList);

        for (Email email : toEmailId) {
            String emailAddress = email.getEmailAddress();
            if (!whitelistEmails.contains(emailAddress.toLowerCase())) {
                try {
                    MimeMessage mimeMessage = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

                    helper.setTo(emailAddress);
                    helper.setSubject(emailDetails.getSubject());
                    helper.setText(emailDetails.getBody());

                    updateEmailStatus(email.getEmailId(), Email.EmailStatus.SENT);

                    mailSender.send(mimeMessage);
                } catch (MessagingException e) {
                    updateEmailStatus(email.getEmailId(), Email.EmailStatus.FAILED);
                    throw new RuntimeException(e);
                }


            }
        }
    }


}




