package com.madan.crud_operations_demo.Controller;

import com.madan.crud_operations_demo.Dto.EmployeeDetailsDTO;
import com.madan.crud_operations_demo.Dto.EmployeesDTO;
import com.madan.crud_operations_demo.Entity.Employees;
import com.madan.crud_operations_demo.Service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeesContoller {

    @Autowired
    private final EmployeesService employeesService;

    public EmployeesContoller(EmployeesService employeesService){

        this.employeesService = employeesService;
    }

    @GetMapping()
    public ResponseEntity<List<EmployeesDTO>> getAllEmployees(){
        return ResponseEntity.ok(employeesService.getAllEmployees());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id){
        System.out.println("End point reachead");
        return ResponseEntity.ok(employeesService.getEmployeeById(id));
    }

    @PostMapping("add")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeesDTO employeeDTO){
        return ResponseEntity.ok(employeesService.addEmployee(employeeDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable int id){
        return ResponseEntity.ok(employeesService.deleteEmployeeById(id));
    }

    @PostMapping("update/{id}")
    public ResponseEntity<?> updateById(@PathVariable int id, @RequestBody EmployeesDTO employeeDTO){
        return ResponseEntity.ok(employeesService.updateById(id, employeeDTO));
    }



}
