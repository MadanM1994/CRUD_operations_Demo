package com.madan.crud_operations_demo.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.madan.crud_operations_demo.dto.EmployeeDetailsDTO;
import com.madan.crud_operations_demo.entity.EmployeeDetails;
import com.madan.crud_operations_demo.repository.EmployeeDetailsRepository;
import com.madan.crud_operations_demo.service.DTOToEntityService;
import com.madan.crud_operations_demo.service.EmployeeDetailsService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Validated
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    private final DTOToEntityService dtoToEntityService;
    private final EmployeeDetailsRepository employeeDetailsRepository;
    private final Validator validator;

    @Autowired

    public EmployeeDetailsServiceImpl(DTOToEntityService dtoToEntityService, EmployeeDetailsRepository employeeDetailsRepository, Validator validator) {
        this.employeeDetailsRepository = employeeDetailsRepository;
        this.dtoToEntityService = dtoToEntityService;
        this.validator = validator;
    }

    @Override
    public void EmployeeDetailsReader(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String filePath = "C:\\Users\\madda\\Documents\\GitHub\\employeeDetails.json";
            List<EmployeeDetailsDTO> employeeDetailsDTOList =
                    mapper.readValue(new File(filePath), new TypeReference<List<EmployeeDetailsDTO>>() {
                    });

            for (EmployeeDetailsDTO employeeDetailsDTO : employeeDetailsDTOList) {
                Set<ConstraintViolation<EmployeeDetailsDTO>> violations = validator.validate(employeeDetailsDTO);
                if (!violations.isEmpty()) {
                    throw new ValidationException(("Validation failed for: " + employeeDetailsDTO + " with violations: " + violations));
                }
                EmployeeDetails employeeDetails = dtoToEntityService.convertToEmployeeDetails(employeeDetailsDTO);
                employeeDetailsRepository.save(employeeDetails);
            }

        } catch (IOException e) {
            log.error("Error reading file: " + filepath, e);
        } catch (ValidationException e) {
            log.error("validation error: ", e);
        }
    }
}
