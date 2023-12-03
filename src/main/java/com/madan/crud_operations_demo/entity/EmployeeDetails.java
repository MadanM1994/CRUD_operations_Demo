package com.madan.crud_operations_demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;


@Entity
@Table(name = "employee_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDetails {


    @Serial
    private static final long serialVersionUID = 1234567L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_details_id")
    private Long employeeDetailsId;

    @Column(name = "first_name", nullable = false)
    private String firstname;

    @Column(name = "last_name", nullable = false)
    private String lastname;

    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

}
