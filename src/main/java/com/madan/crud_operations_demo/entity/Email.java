package com.madan.crud_operations_demo.entity;

import com.madan.crud_operations_demo.constants.EmailStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "email")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
