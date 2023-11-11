package com.madan.crud_operations_demo.Dto;

import com.madan.crud_operations_demo.Entity.Address;
import com.madan.crud_operations_demo.Entity.ContactInformation;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.internal.util.StringHelper;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EmployeesDTO {
    private int employeeId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private char gender;
//    private List<Address> address;
//    private List<ContactInformationDTO> contactInformation;
}
