package com.madan.crud_operations_demo.api.service;

import com.madan.crud_operations_demo.dto.*;
import com.madan.crud_operations_demo.entity.*;
import com.madan.crud_operations_demo.implementation.EntityToDTOServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EntityToDTOServiceImplTest {

    EntityToDTOServiceImpl convertService = new EntityToDTOServiceImpl();

    @Test
    public void entityToDTOServiceImplTest_ConvertToEmployeeDTO_ReturnEmployeeDTO() {
        Address address = Address.builder()
                .streetName("6051 etter")
                .city("ijamsville")
                .state("MD")
                .zipCode("6051")
                .isDeleted('N')
                .isActive('Y')
                .addressId(1)
                .build();
        Set<Address> addresses = new HashSet<>();
        addresses.add(address);
        ContactInformation contactInformation = ContactInformation.builder()
                .email("maddala.madan@gmail.com")
                .phoneNumber("+1(123)-456-7890")
                .build();
        Date dob = new Date();
        Set<ContactInformation> contactInformations = new HashSet<>();
        contactInformations.add(contactInformation);
        Employees employees = Employees.builder()
                .firstName("Madan")
                .lastName("Maddala")
                .gender("Male")
                .dateOfBirth(dob)
                .employeeId(1)
                .contactInformations(contactInformations)
                .addresses(addresses)
                .build();

        EmployeesDTO employeesDTO = convertService.convertToEmployeesDTO(employees);

        assertEquals("Male", employeesDTO.getGender());
        assertEquals("Maddala", employeesDTO.getLastName());
    }

    @Test
    public void entityToDTOServiceImplTest_ConvertToAddressDTO_ReturnAddressDTO() {
        Date dob = new Date();
        Employees employees = Employees.builder()
                .firstName("Madan")
                .lastName("Maddala")
                .gender("Male")
                .dateOfBirth(dob)
                .employeeId(1)
                .build();
        Address address = Address.builder()
                .streetName("6051 etter")
                .city("ijamsville")
                .state("MD")
                .zipCode("6051")
                .employees(employees)
                .isDeleted('N')
                .isActive('Y')
                .addressId(1)
                .build();

        AddressDTO addressDTO = convertService.convertToAddressDTO(address);

        assertEquals("ijamsville", addressDTO.getCity());
        assertEquals("6051", addressDTO.getZipCode());
    }

    @Test
    public void entityToDTOServiceImplTest_ConvertToContactInformationDTO_ReturnContactInformationDTO() {
        Date dob = new Date();
        Employees employees = Employees.builder()
                .firstName("Madan")
                .lastName("Maddala")
                .gender("Male")
                .dateOfBirth(dob)
                .employeeId(1)
                .build();
        ContactInformation contactInformation = ContactInformation.builder()
                .email("maddala.madan@gmail.com")
                .phoneNumber("+1(123)-456-7890")
                .employees(employees)
                .build();

        ContactInformationDTO contactInformationDTO = convertService.convertToContactInformationDTO(contactInformation);

        assertEquals("+1(123)-456-7890", contactInformationDTO.getPhoneNumber());
    }

    @Test
    public void entityToDTOServiceImplTest_ConvertToEmailDTO_ReturnEmailDTO() {
        Email email = Email.builder()
                .emailAddress("maddala.madan@gmail.com")
                .emailId(1)
                .build();

        EmailDTO emailDTO = convertService.convertToEmailDTO(email);

        assertEquals("maddala.madan@gmail.com", emailDTO.getEmailAddress());
    }

    @Test
    public void entityToDTOServiceImplTest_ConvertToEmployeeDetailsDTO_ReturnEmployeeDetailsDTO() {
        EmployeeDetails employeeDetails = EmployeeDetails.builder()
                .employeeDetailsId(1L)
                .firstname("Madan")
                .lastname("Maddala")
                .email("maddala.madan@gmail.com")
                .phoneNumber("7034620905")
                .zipcode("12345")
                .build();

        EmployeeDetailsDTO employeeDetailsDTO = convertService.convertToEmployeeDetailsDTO(employeeDetails);

        assertEquals("Madan", employeeDetailsDTO.getFirstname());
        assertEquals("12345", employeeDetailsDTO.getZipcode());
        assertEquals("maddala.madan@gmail.com", employeeDetailsDTO.getEmail());

    }


}
