package com.madan.crud_operations_demo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "address")
@Data
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @Column(name="employee_id")
    private int employeeId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employees employees;

    @Column(name="street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private int zipCode;

    @Column(name = "is_active")
    private char active;

    @Column(name = "is_deleted")
    private char deleted;

    @OneToMany
    private List<ContactInformation> contactInformationList;

    @OneToMany
    private List<Address> addressList;
}
