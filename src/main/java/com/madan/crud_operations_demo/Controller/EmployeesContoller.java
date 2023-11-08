package com.madan.crud_operations_demo.Controller;

import com.madan.crud_operations_demo.Entity.Employees;
import com.madan.crud_operations_demo.Service.EmployeesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employees")
public class EmployeesContoller {

    private final EmployeesService employeesService;

    public EmployeesContoller(EmployeesService employeesService){
        this.employeesService = employeesService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllEmployees(){
        return ResponseEntity.ok(employeesService.getAllEmployees());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id){
        System.out.println("End point reachead");
        return ResponseEntity.ok(employeesService.getEmployeeById(id));
    }

    @PostMapping("add")
    public ResponseEntity<?> addEmployee(@RequestBody Employees employee){
        return ResponseEntity.ok(employeesService.addEmployee(employee));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable int id){
        return ResponseEntity.ok(employeesService.deleteEmployeeById(id));


    }



}
