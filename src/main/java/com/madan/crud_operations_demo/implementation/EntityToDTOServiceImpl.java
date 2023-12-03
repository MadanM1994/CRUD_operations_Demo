package com.madan.crud_operations_demo.implementation;

import com.madan.crud_operations_demo.dto.*;
import com.madan.crud_operations_demo.entity.*;
import com.madan.crud_operations_demo.service.EntityToDTOService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EntityToDTOServiceImpl implements EntityToDTOService {

    public EmployeesDTO convertToEmployeesDTO(Employees employee) {
        EmployeesDTO newEmployeesDTO = new EmployeesDTO();
        BeanUtils.copyProperties(employee, newEmployeesDTO);
        return newEmployeesDTO;
    }

    public ContactInformationDTO convertToContactInformationDTO(ContactInformation contactInformation) {
        ContactInformationDTO newContactInformationDTO = new ContactInformationDTO();
        BeanUtils.copyProperties(contactInformation, newContactInformationDTO);
        return newContactInformationDTO;
    }

    public AddressDTO convertToAddressDTO(Address address) {
        AddressDTO newAddressDTO = new AddressDTO();
        BeanUtils.copyProperties(address, newAddressDTO);
        return newAddressDTO;
    }

    public EmailDTO convertToEmailDTO(Email email) {
        EmailDTO newEmailDTO = new EmailDTO();
        BeanUtils.copyProperties(email, newEmailDTO);
        return newEmailDTO;
    }

    public EmployeeDetailsDTO convertToEmployeeDetailsDTO(EmployeeDetails employeeDetails) {
        EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
        BeanUtils.copyProperties(employeeDetails, employeeDetailsDTO);
        return employeeDetailsDTO;
    }

}

