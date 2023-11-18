package com.madan.crud_operations_demo.implementation;

import com.madan.crud_operations_demo.dto.AddressDTO;
import com.madan.crud_operations_demo.dto.ContactInformationDTO;
import com.madan.crud_operations_demo.dto.EmailDTO;
import com.madan.crud_operations_demo.dto.EmployeesDTO;
import com.madan.crud_operations_demo.entity.Address;
import com.madan.crud_operations_demo.entity.ContactInformation;
import com.madan.crud_operations_demo.entity.Email;
import com.madan.crud_operations_demo.entity.Employees;
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

    private ContactInformationDTO convertTocontactInformationDTO(ContactInformation contactInformation) {
        ContactInformationDTO newContactInformationDTO = new ContactInformationDTO();
        BeanUtils.copyProperties(contactInformation, newContactInformationDTO);
        return newContactInformationDTO;
    }

    private AddressDTO convertToaddressDTO(Address address) {
        AddressDTO newAddressDTO = new AddressDTO();
        BeanUtils.copyProperties(address, newAddressDTO);
        return newAddressDTO;
    }

    private EmailDTO convertToEmailDTO(Email email) {
        EmailDTO newEmailDTO = new EmailDTO();
        BeanUtils.copyProperties(email, newEmailDTO);
        return newEmailDTO;
    }
}

