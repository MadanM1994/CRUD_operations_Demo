package com.madan.crud_operations_demo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "contact_information")
@Data
public class ContactInformation {

    @Id
    @Column(name = "contact_information_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactInformationId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employees employees;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_active")
    private char active;

    @Column(name = "is_deleted")
    private char deleted;
}
