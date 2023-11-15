package com.madan.crud_operations_demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "contact_information")
@Data
public class ContactInformation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567L;


    @Id
    @Column(name = "contact_information_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactInformationId;

//    @ManyToOne
//    @JoinColumn(name = "employee_id")
//    @Persistent
//    private Employees employees;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_active")
    private char active;

    @Column(name = "is_deleted")
    private char deleted;
}
