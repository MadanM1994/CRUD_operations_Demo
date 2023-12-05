package com.madan.crud_operations_demo.service;

import com.madan.crud_operations_demo.dto.EmployeesDTO;
import com.madan.crud_operations_demo.projection.EmployeeByNameProjection;

import java.util.List;

public interface EmployeesService {

    // GET ALL EMPLOYEES
    List<EmployeesDTO> getAllEmployees();

    //  GET EMPLOYEES BY ID
    EmployeesDTO getEmployeeById(int id);

    // GET EMPLOYEES BY NAME
    List<EmployeeByNameProjection> getEmployeeDetailsByMatch(String name);

    // CREATE EMPLOYEE
    String addEmployee(EmployeesDTO employeesDTO);

    // DELETE EMPLOYEE
    String deleteEmployeeById(int id);

}
