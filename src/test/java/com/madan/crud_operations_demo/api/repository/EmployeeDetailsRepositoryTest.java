package com.madan.crud_operations_demo.api.repository;

import com.madan.crud_operations_demo.entity.EmployeeDetails;
import com.madan.crud_operations_demo.repository.EmployeeDetailsRepository;
import jakarta.validation.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EmployeeDetailsRepositoryTest {

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private Validator validator;

    @Test
    public void contextLoads() {
    }

    @Test
    public void employeeDetailsRepository_SaveEmployeeDetails_ReturnSavedEntity() {
        EmployeeDetails employeeDetails = EmployeeDetails.builder()
                .firstname("Madan")
                .lastname("Maddala")
                .email("maddala.madan@gmail.com")
                .phoneNumber("7034620905")
                .zipcode("12345")
                .build();

        EmployeeDetails savedEmployeeDetails = employeeDetailsRepository.save(employeeDetails);

        Assertions.assertThat(savedEmployeeDetails).isNotNull();
        Assertions.assertThat(savedEmployeeDetails.getEmployeeDetailsId()).isNotNull();
        Assertions.assertThat(savedEmployeeDetails.getFirstname()).isEqualTo("Madan");
        Assertions.assertThat(savedEmployeeDetails.getLastname()).isEqualTo("Maddala");
    }
}
