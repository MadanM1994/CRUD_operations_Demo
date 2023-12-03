package com.madan.crud_operations_demo.api.validation;

import com.madan.crud_operations_demo.dto.EmployeeDetailsDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ValidationsTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void employeeDetailsDTO_AllValidations_ReturnIsValid() {
        EmployeeDetailsDTO employeeDetailsDTO = EmployeeDetailsDTO.builder()
                .zipcode("12345")
                .phoneNumber("0123456789")
                .email("maddala.madan@gmail.com")
                .lastname("Maddala")
                .firstname("Madan")
                .build();

        Set<ConstraintViolation<EmployeeDetailsDTO>> violations = validator.validate(employeeDetailsDTO);
        assertTrue(violations.isEmpty(), "No violations should be present for a valid name");
    }

    @Test
    public void employeeDetailsDTO_NameValidation_ReturnIsInvalid() {
        EmployeeDetailsDTO employeeDetailsDTO = EmployeeDetailsDTO.builder()
                .zipcode("1234")
                .phoneNumber("012345679")
                .email("maddala.madangmail.com")
                .lastname("M")
                .firstname("Ma")
                .build();

        Set<ConstraintViolation<EmployeeDetailsDTO>> violations = validator.validate((employeeDetailsDTO));
        assertEquals(5, violations.size());
    }
}
