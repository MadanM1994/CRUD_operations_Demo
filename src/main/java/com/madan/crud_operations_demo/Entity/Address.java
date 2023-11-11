package com.madan.crud_operations_demo.Entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Persistent;

import java.io.Serializable;

@Entity
@Table(name = "address")
@Setter
@Getter
@EqualsAndHashCode
public class Address implements Serializable {

    private static final long serialVersionUID = 1234567L;


    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @Persistent
    private Employees employees;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "is_active")
    private char active;

    @Column(name = "is_deleted")
    private char deleted;
}
