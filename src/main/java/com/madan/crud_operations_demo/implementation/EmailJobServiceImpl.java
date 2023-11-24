package com.madan.crud_operations_demo.implementation;

import com.madan.crud_operations_demo.constants.EmailStatus;
import com.madan.crud_operations_demo.entity.Email;
import com.madan.crud_operations_demo.repository.EmailRepository;
import com.madan.crud_operations_demo.service.EmailJobService;
import org.springframework.stereotype.Service;

@Service
public class EmailJobServiceImpl implements EmailJobService {
    private final EmailRepository emailRepository;

    public EmailJobServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
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

}