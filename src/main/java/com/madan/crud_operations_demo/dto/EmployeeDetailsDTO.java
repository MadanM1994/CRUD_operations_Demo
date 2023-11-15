package com.madan.crud_operations_demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDetailsDTO {
    private EmployeesDTO employees;
    private ContactInformationDTO contactInformation;
    private AddressDTO address;
    private EmailDTO email;
}
