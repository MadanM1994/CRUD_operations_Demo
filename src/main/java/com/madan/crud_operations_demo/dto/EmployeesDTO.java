package com.madan.crud_operations_demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeesDTO {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private char gender;
    private String email;
//    private List<Address> address;
//    private List<ContactInformationDTO> contactInformation;
}
