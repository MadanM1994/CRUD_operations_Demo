package com.madan.crud_operations_demo.service;

import com.madan.crud_operations_demo.dto.AddressDTO;
import com.madan.crud_operations_demo.dto.ContactInformationDTO;
import com.madan.crud_operations_demo.dto.EmployeesDTO;
import com.madan.crud_operations_demo.entity.Address;
import com.madan.crud_operations_demo.entity.ContactInformation;
import com.madan.crud_operations_demo.entity.Employees;

public interface DTOToEntityService {
    Employees convertToEmployees(EmployeesDTO employeesDTO);

    Address convertToAddress(AddressDTO addressDTO);

    ContactInformation convertToContactInformation(ContactInformationDTO contactInformationDTO);
}
