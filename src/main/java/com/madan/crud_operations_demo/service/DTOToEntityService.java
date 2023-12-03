package com.madan.crud_operations_demo.service;

import com.madan.crud_operations_demo.dto.*;
import com.madan.crud_operations_demo.entity.*;

public interface DTOToEntityService {
    Employees convertToEmployees(EmployeesDTO employeesDTO);

    Address convertToAddress(AddressDTO addressDTO);

    ContactInformation convertToContactInformation(ContactInformationDTO contactInformationDTO);

    Email convertToEmail(EmailDTO emailDTO);

    EmployeeDetails convertToEmployeeDetails(EmployeeDetailsDTO employeeDetailsDTO);
}
