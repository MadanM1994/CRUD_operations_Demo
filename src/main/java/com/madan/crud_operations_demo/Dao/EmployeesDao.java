package com.madan.crud_operations_demo.Dao;

import com.madan.crud_operations_demo.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesDao extends JpaRepository <Employees, Integer> {
}
