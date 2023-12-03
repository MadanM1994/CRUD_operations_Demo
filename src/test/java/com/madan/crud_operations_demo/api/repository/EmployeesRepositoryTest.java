package com.madan.crud_operations_demo.api.repository;

import com.madan.crud_operations_demo.entity.Address;
import com.madan.crud_operations_demo.entity.ContactInformation;
import com.madan.crud_operations_demo.entity.Employees;
import com.madan.crud_operations_demo.projection.EmployeeByNameProjection;
import com.madan.crud_operations_demo.repository.AddressRepository;
import com.madan.crud_operations_demo.repository.ContactInformationRepository;
import com.madan.crud_operations_demo.repository.EmployeesRepository;
import jakarta.validation.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

public class EmployeesRepositoryTest {

    @MockBean
    private JavaMailSender javaMailSender;

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private ContactInformationRepository contactInformationRepository;

    @Autowired
    private AddressRepository addressRepository;
    
    @MockBean
    private Validator validator;

    @AfterEach
    public void cleanup() {
        contactInformationRepository.deleteAll();
        addressRepository.deleteAll();
        employeesRepository.deleteAll();
    }

    @Test
    public void employeesRepository_GetAllEmployees_ReturnListOfEmployees() throws ParseException {
        //Action
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        Date dateOfBirth1 = formatter.parse("10-02-1994");
        Date dateOfBirth2 = formatter.parse("09-24-1992");

        //Arrange
        Employees employees1 = Employees.builder()
                .firstName("Madan")
                .lastName("Maddala")
                .gender("Male")
                .dateOfBirth(dateOfBirth1)
                .build();

        Employees employees2 = Employees.builder()
                .firstName("Chandu")
                .lastName("Adabala")
                .gender("Male")
                .dateOfBirth(dateOfBirth2)
                .build();

        //Act
        employeesRepository.save(employees1);
        employeesRepository.save(employees2);
        List<Employees> allEmployees = employeesRepository.findAll();

        //Assert
        Assertions.assertThat(allEmployees).isNotNull();
        Assertions.assertThat(allEmployees.size()).isEqualTo(2);
    }

    @Test
    public void employeesRepository_GetEmployeeById_ReturnEmployee() throws ParseException {
        //Arrange
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        Date dateOfBirth1 = formatter.parse("10-02-1994");
        Employees employees1 = Employees.builder()
                .firstName("Madan")
                .lastName("Maddala")
                .gender("Male")
                .dateOfBirth(dateOfBirth1)
                .build();
        Employees savedEmployee = employeesRepository.save(employees1);

        //Act
        Optional<Employees> employeeRecord = employeesRepository.findById(savedEmployee.getEmployeeId());

        //Assertion
        Assertions.assertThat(employeeRecord.isPresent()).isTrue();
        Assertions.assertThat(employeeRecord.get().getFirstName()).isEqualTo("Madan");
        Assertions.assertThat(employeeRecord.get().getGender()).isEqualTo("Male");

    }

    @Test
    public void employeeRepository_CreateEmployee_ReturnEmployee() throws ParseException {
        //Arrange
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        Date dateOfBirth1 = formatter.parse("10-02-1994");
        Employees employees1 = Employees.builder()
                .firstName("Madan")
                .lastName("Maddala")
                .gender("Male")
                .dateOfBirth(dateOfBirth1)
                .build();

        // Act
        Employees savedEmployee = employeesRepository.save(employees1);

        //Assertion
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getFirstName()).isEqualTo("Madan");
        Assertions.assertThat(savedEmployee.getLastName()).isEqualTo("Maddala");
    }

    @Test
    public void employeeRepository_DeleteEmployee_ReturnEmployee() throws ParseException {
        //Arrange
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        Date dateOfBirth1 = formatter.parse("10-02-1994");
        Employees employees1 = Employees.builder()
                .firstName("Madan")
                .lastName("Maddala")
                .gender("Male")
                .dateOfBirth(dateOfBirth1)
                .build();
//        Employees employees1 = Mockito.mock(Employees.class);
        Employees savedEmployee = employeesRepository.save(employees1);

        //Act
        employeesRepository.deleteById(savedEmployee.getEmployeeId());

        //Assertion
        Optional<Employees> deletedRecord = employeesRepository.findById(savedEmployee.getEmployeeId());
        Assertions.assertThat(deletedRecord.isPresent()).isFalse();

    }

    @Test
    public void employeesRepository_EmployeesProjection_ReturnEmployeesProjection() throws ParseException {
        //Arrange
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        Date dateOfBirth1 = formatter.parse("10-02-1994");

        //Arrange
        Employees employees1 = Employees.builder()
                .firstName("Madan")
                .lastName("Maddala")
                .gender("Male")
                .dateOfBirth(dateOfBirth1)
                .build();

        ContactInformation contactInformation = ContactInformation.builder()
                .email("maddala.madan@gmail.com")
                .phoneNumber("+1 (703)-462-0905")
                .employees(employees1)
                .build();

        Address address = Address.builder()
                .streetName("6051 etterbeek street")
                .city("Ijamsville")
                .state("MD")
                .zipCode("1234")
                .employees(employees1)
                .build();
        //Action
        employeesRepository.save(employees1);
        contactInformationRepository.save(contactInformation);
        addressRepository.save(address);

        String name = "Madan";

        //Act
        List<EmployeeByNameProjection> record = employeesRepository.findEmployeeDetailsByFirstName(name);

        //Assertion
        Assertions.assertThat(record.isEmpty()).isFalse();
        EmployeeByNameProjection result = record.get(0);
        Assertions.assertThat(result.getCity()).isEqualTo("Ijamsville");
        Assertions.assertThat(result.getEmail()).isEqualTo("maddala.madan@gmail.com");
        Assertions.assertThat(result.getLastName()).isEqualTo("Maddala");
        Assertions.assertThat(result.getFirstName()).isEqualTo("Madan");
        Assertions.assertThat(result.getEmployeeId()).isNotNull();
        Assertions.assertThat(result.getDateOfBirth()).isNotNull();

    }
}

