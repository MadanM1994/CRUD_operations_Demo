package com.madan.crud_operations_demo.api.service;

import com.madan.crud_operations_demo.dto.EmployeeDetailsDTO;
import com.madan.crud_operations_demo.entity.EmployeeDetails;
import com.madan.crud_operations_demo.implementation.EmployeeDetailsServiceImpl;
import com.madan.crud_operations_demo.repository.EmployeeDetailsRepository;
import com.madan.crud_operations_demo.service.DTOToEntityService;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeDetailsServiceTest {

    @Mock
    private DTOToEntityService dtoToEntityService;

    @Mock
    private EmployeeDetailsRepository employeeDetailsRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private EmployeeDetailsServiceImpl employeeDetailsService;


    @Test
    public void employeeDetailsService_EmployeeDetailsReader_ReturnSavedEntity() throws IOException {
        // Arrange
        String filePath = "testfile.json";
        EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
        EmployeeDetails employeeDetails = new EmployeeDetails();
        when(validator.validate(any(EmployeeDetailsDTO.class))).thenReturn(Collections.emptySet());
        when(dtoToEntityService.convertToEmployeeDetails(any(EmployeeDetailsDTO.class))).thenReturn(employeeDetails);

        // Act
        employeeDetailsService.EmployeeDetailsReader(filePath);

        // Assert
        verify(employeeDetailsRepository, times(2)).save(employeeDetails);
        verify(dtoToEntityService, times(2)).convertToEmployeeDetails(any(EmployeeDetailsDTO.class));

    }

}
