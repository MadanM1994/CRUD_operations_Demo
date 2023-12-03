package com.madan.crud_operations_demo.controller;

import com.madan.crud_operations_demo.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("employees-file")
public class EmployeeDetailsController {

    @Autowired
    private EmployeeDetailsService employeeDetailsService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadEmployeesFile(@RequestParam String filePath) throws IOException {
        try {
            employeeDetailsService.EmployeeDetailsReader(filePath);
            return
                    ResponseEntity.ok("FIle read successfully");
        } catch (IOException e) {
            return
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading file");
        }
    }

}
