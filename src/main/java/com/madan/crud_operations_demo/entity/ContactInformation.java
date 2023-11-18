package com.madan.crud_operations_demo.entity;

import com.madan.crud_operations_demo.entity.Employees;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "contact_information")
public class ContactInformation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contactInfoId;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employees employees;

    @Column(name = "is_active")
    private char isActive;

    @Column(name = "is_deleted")
    private char isDeleted;

}
