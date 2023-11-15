package com.madan.crud_operations_demo.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
@EqualsAndHashCode
@Getter
@Setter
public class Employees implements Serializable {

    private static final long serialVersionUID = 1234567L;

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

    @Column(name = "email")
    private String email;

//    // One-to-Many relationship with Address entities
//    @OneToMany(mappedBy = "employees", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnoreProperties("employees") // Ignore the 'employees' property in Address during JSON serialization
//    private List<Address> addresses;
//
//    // One-to-Many relationship with ContactInformation entities
//    @OneToMany(mappedBy = "employees", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnoreProperties("employees") // Ignore the 'employees' property in ContactInformation during JSON serialization
//    private List<ContactInformation> contactInformation;
}
