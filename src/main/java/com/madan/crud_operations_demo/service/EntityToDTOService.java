package com.madan.crud_operations_demo.service;

import com.madan.crud_operations_demo.dto.EmployeesDTO;
import com.madan.crud_operations_demo.entity.Employees;

public interface EntityToDTOService {
    EmployeesDTO convertToEmployeesDTO(Employees employee);
}
