package com.madan.crud_operations_demo.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "email")
@Getter
@Setter
public class Email implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id")
    private int emailId;

    @Column(name = "`to_email_address`")
    private String emailAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "email_status")
    private EmailStatus emailStatus;

    // Enum for email status
    public enum EmailStatus {
        CREATED, SENT, FAILED
    }


}
