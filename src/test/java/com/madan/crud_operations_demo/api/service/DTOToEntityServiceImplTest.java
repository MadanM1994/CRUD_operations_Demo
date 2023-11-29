package com.madan.crud_operations_demo.api.service;

import com.madan.crud_operations_demo.dto.AddressDTO;
import com.madan.crud_operations_demo.dto.ContactInformationDTO;
import com.madan.crud_operations_demo.dto.EmailDTO;
import com.madan.crud_operations_demo.dto.EmployeesDTO;
import com.madan.crud_operations_demo.entity.Address;
import com.madan.crud_operations_demo.entity.ContactInformation;
import com.madan.crud_operations_demo.entity.Email;
import com.madan.crud_operations_demo.entity.Employees;
import com.madan.crud_operations_demo.implementation.DTOToEntityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class DTOToEntityServiceImplTest {
    DTOToEntityServiceImpl convertService = new DTOToEntityServiceImpl();

    @Test
    public void DTOToEntityService_ConvertToEmployees_ReturnEmployeesEntity() {
        AddressDTO addressDTO = AddressDTO.builder()
                .streetName("6051 etter")
                .city("ijamsville")
                .state("MD")
                .zipCode("6051")
                .build();
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);
        ContactInformationDTO contactInformationDTO = ContactInformationDTO.builder()
                .email("maddala.madan@gmail.com")
                .phoneNumber("+1(123)-456-7890")
                .build();
        List<ContactInformationDTO> contactInformations = Collections.singletonList(contactInformationDTO);
        Date dob = new Date();
        EmployeesDTO employeesDTO = EmployeesDTO.builder()
                .firstName("Madan")
                .lastName("Maddala")
                .dateOfBirth(dob)
                .gender("Male")
                .addresses(addresses)
                .contactInformation(contactInformations)
                .build();


        Employees convertedEmployee = convertService.convertToEmployees(employeesDTO);

        assertEquals("Madan", convertedEmployee.getFirstName());
        assertEquals("Male", convertedEmployee.getGender());
        assertNotNull(employeesDTO.getAddresses());
        assertNotNull(employeesDTO.getContactInformation());
    }

    @Test
    public void DTOToEntityService_ConvertToAddressEntity_ReturnAddressEntity() {
        AddressDTO addressDTO = AddressDTO.builder()
                .streetName("6051 etter")
                .city("ijamsville")
                .state("MD")
                .zipCode("6051")
                .build();

        Address convertedAddress = convertService.convertToAddress(addressDTO);

        assertEquals("6051", convertedAddress.getZipCode());
        assertEquals("6051 etter", convertedAddress.getStreetName());
        assertNull(convertedAddress.getEmployees());

    }

    @Test
    public void DTOToEntityService_ConvertToContactInformationEntity_ReturnContactInformationEntity() {
        ContactInformationDTO contactInformationDTO = ContactInformationDTO.builder()
                .email("maddala.madan@gmail.com")
                .phoneNumber("+1(123)-456-7890")
                .build();

        ContactInformation convertedContactInformation = convertService.convertToContactInformation(contactInformationDTO);

        assertEquals("+1(123)-456-7890", convertedContactInformation.getPhoneNumber());
        assertNull(convertedContactInformation.getEmployees());
    }

    @Test
    public void DTOToEntityService_ConvertToEmailEntity_ReturnEmailEntity() {
        EmailDTO emailDTO = EmailDTO.builder()
                .emailAddress("maddala.madan@gmail.com")
                .build();

        Email convertedEmail = convertService.convertToEmail(emailDTO);

        assertEquals("maddala.madan@gmail.com", convertedEmail.getEmailAddress());
//        assertNotNull(convertedEmail.getEmailId());
    }
}
