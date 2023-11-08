package com.madan.crud_operations_demo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="employees")
@Data
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "gender")
    private char gender;

    @Column(name = "is_active")
    private char active;

    @Column(name = "is_deleted")
    private char deleted;

    @OneToMany(mappedBy = "employees", cascade = CascadeType.ALL) // Define the one-to-many relationship with Address
    private List<Address> addresses;

    @OneToMany(mappedBy = "employees", cascade = CascadeType.ALL) // Define the one-to-many relationship with ContactInformation
    private List<ContactInformation> contactInformation;

}
