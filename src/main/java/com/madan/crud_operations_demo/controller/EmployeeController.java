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

    @GetMapping("employee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
        return ResponseEntity.ok(employeesService.getEmployeeById(id));
    }

    @PostMapping("update/{id}")
    public ResponseEntity<?> updateById(@PathVariable int id, @RequestBody EmployeesDTO employeeDTO) {
        return ResponseEntity.ok(employeesService.updateById(id, employeeDTO));
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<EmployeeByNameProjection>> getEmployeeDetailsByMatch(@PathVariable String name) {
        return ResponseEntity.ok(employeesService.getEmployeeDetailsByMatch(name));
    }

    @PostMapping("create")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeesDTO employeeDTO) {
        return ResponseEntity.ok(employeesService.addEmployee(employeeDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable int id) {
        return ResponseEntity.ok(employeesService.deleteEmployeeById(id));
    }

}
