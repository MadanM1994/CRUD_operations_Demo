package com.madan.crud_operations_demo.service;

import com.madan.crud_operations_demo.dto.EmployeesDTO;
import com.madan.crud_operations_demo.projection.EmployeeByNameProjection;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
    Boolean deleteEmployeeById(int id);

    // UPDATE EMPLOYEE BY ID
    String updateById(@PathVariable int id, @RequestBody EmployeesDTO employeeDTO);
}
