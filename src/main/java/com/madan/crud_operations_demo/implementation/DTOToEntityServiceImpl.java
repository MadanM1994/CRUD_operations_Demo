package com.madan.crud_operations_demo.implementation;

import com.madan.crud_operations_demo.dto.*;
import com.madan.crud_operations_demo.entity.*;
import com.madan.crud_operations_demo.service.DTOToEntityService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DTOToEntityServiceImpl implements DTOToEntityService {
    public Employees convertToEmployees(EmployeesDTO employeesDTO) {
        Employees newEmployees = new Employees();
        BeanUtils.copyProperties(employeesDTO, newEmployees);
        newEmployees.setIsActive('Y');
        newEmployees.setIsDeleted('N');
        return newEmployees;
    }

    public Address convertToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        // Additional properties setup if needed
        return address;
    }

    public ContactInformation convertToContactInformation(ContactInformationDTO contactInfoDTO) {
        ContactInformation contactInformation = new ContactInformation();
        BeanUtils.copyProperties(contactInfoDTO, contactInformation);
        // Additional properties setup if needed
        return contactInformation;
    }

    public Email convertToEmail(EmailDTO emailDTO) {
        Email newEmails = new Email();
        BeanUtils.copyProperties(emailDTO, newEmails);
        return newEmails;
    }

    public EmployeeDetails convertToEmployeeDetails(EmployeeDetailsDTO employeeDetailsDTO) {
        EmployeeDetails newEmployeeDetails = new EmployeeDetails();
        BeanUtils.copyProperties(employeeDetailsDTO, newEmployeeDetails);
        return newEmployeeDetails;
    }

}
