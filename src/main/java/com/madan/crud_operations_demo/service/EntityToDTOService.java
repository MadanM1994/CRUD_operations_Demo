package com.madan.crud_operations_demo.service;

import com.madan.crud_operations_demo.dto.*;
import com.madan.crud_operations_demo.entity.*;

public interface EntityToDTOService {
    EmployeesDTO convertToEmployeesDTO(Employees employee);

    ContactInformationDTO convertToContactInformationDTO(ContactInformation contactInformation);

    AddressDTO convertToAddressDTO(Address address);

    EmailDTO convertToEmailDTO(Email email);

    EmployeeDetailsDTO convertToEmployeeDetailsDTO(EmployeeDetails employeeDetails);
}
