package com.madan.crud_operations_demo.Service;

import com.madan.crud_operations_demo.Repository.EmployeesDao;
import com.madan.crud_operations_demo.Entity.Employees;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesService {

    private final EmployeesDao employeesDao;

    public EmployeesService(EmployeesDao employeesDao){
        this.employeesDao= employeesDao;
    }


    public List<Employees> getAllEmployees() {
        return employeesDao.findAll();
    }

    public Employees getEmployeeById(int id) {
        return employeesDao.findById(id).get();
    }

    @Transactional
    public String addEmployee( Employees employee) {
            employeesDao.save(employee);
            return "Posted Successfully";
    }

    public String deleteEmployeeById(int id) {
        employeesDao.deleteById(id);
        return "Successfully Deleted"; // Employee deletion was successful
    }
}
