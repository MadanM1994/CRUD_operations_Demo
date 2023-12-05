package com.madan.crud_operations_demo.controller;

import com.madan.crud_operations_demo.dto.EmployeesDTO;
import com.madan.crud_operations_demo.projection.EmployeeByNameProjection;
import com.madan.crud_operations_demo.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeesService employeesService;

    @Autowired
    public EmployeeController(EmployeesService employeesService) {

        this.employeesService = employeesService;
    }

    @GetMapping()
    public ResponseEntity<List<EmployeesDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeesService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
        EmployeesDTO employees = employeesService.getEmployeeById(id);
        return ResponseEntity.ok(employees);
    }


    @GetMapping("/by-firstname/{name}")
    public ResponseEntity<?> getEmployeeDetailsByMatch(@PathVariable String name) {
        List<EmployeeByNameProjection> employeeDetails = employeesService.getEmployeeDetailsByMatch(name);
        return ResponseEntity.ok(employeeDetails);
    }


    @PostMapping("/create")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeesDTO employeeDTO) {
        return ResponseEntity.ok(employeesService.addEmployee(employeeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable int id) {
        return ResponseEntity.ok(employeesService.deleteEmployeeById(id));
    }

}
