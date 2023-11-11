package com.madan.crud_operations_demo.Dto;

import com.madan.crud_operations_demo.Entity.Address;
import com.madan.crud_operations_demo.Entity.ContactInformation;
import com.madan.crud_operations_demo.Entity.Employees;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDetailsDTO {
    private EmployeesDTO employees;
    private ContactInformationDTO contactInformation;
    private AddressDTO address;
}
