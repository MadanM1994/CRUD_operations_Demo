package com.madan.crud_operations_demo.repository;

import com.madan.crud_operations_demo.entity.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInformationRepository extends JpaRepository<ContactInformation, Integer> {
    @Modifying
    @Query("DELETE FROM ContactInformation c WHERE c.employees.employeeId = :employeeId")
    void deleteByEmployeeId(int employeeId);

}
