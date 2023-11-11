package com.madan.crud_operations_demo.Repository;

import com.madan.crud_operations_demo.Entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends JpaRepository <Employees, Integer> {
}