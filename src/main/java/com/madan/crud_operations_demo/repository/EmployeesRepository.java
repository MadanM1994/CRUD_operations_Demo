package com.madan.crud_operations_demo.repository;

import com.madan.crud_operations_demo.entity.Employees;
import com.madan.crud_operations_demo.projection.EmployeeByNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

    @Query("SELECT e.employeeId as employeeId, e.firstName as firstName, e.lastName as lastName, ci.email as email, e.dateOfBirth as dateOfBirth, a.city as city " +
            "FROM Employees e " +
            "JOIN e.addresses a " +
            "JOIN e.contactInformations ci " +
            "WHERE e.firstName LIKE %:name% OR e.lastName LIKE %:name%")
    List<EmployeeByNameProjection> findEmployeeDetailsByFirstName(@Param("name") String name);
}

