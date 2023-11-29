package com.madan.crud_operations_demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @Column(name = "street_name")
    private String streetName;

    private String city;

    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employees employees;

    @Column(name = "is_active")
    private char isActive;

    @Column(name = "is_deleted")
    private char isDeleted;

}
