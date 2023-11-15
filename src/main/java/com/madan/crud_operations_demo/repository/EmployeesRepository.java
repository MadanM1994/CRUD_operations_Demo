package com.madan.crud_operations_demo.repository;

import com.madan.crud_operations_demo.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends JpaRepository <Employees, Integer> {
}
